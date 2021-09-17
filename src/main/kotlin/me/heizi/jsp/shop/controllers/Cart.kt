package me.heizi.jsp.shop.controllers

import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.mvc.Controller
import jakarta.mvc.Models
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Response
import me.heizi.jsp.shop.R
import me.heizi.jsp.shop.annotations.Open
import me.heizi.jsp.shop.dao.OrderDao
import me.heizi.jsp.shop.services.LoginService

/**
 * Cart
 *
 * @GET 阻止用户不存在 存在选择用户的数据
 * @POST 把购物车内的产品移动到订单表内
 * @DELETE 删除购物车内的玩意
 * @PUT 更新购物车的数量
 */
@Path("/cart")
@Controller
@Open class Cart {

    @Inject private lateinit var login:LoginService
    @RequestScoped
    @Inject private lateinit var model:Models
    @Inject private lateinit var dao:OrderDao

    private fun getResponse(id:Int):Response {
        model.put("list",dao.getPreOrdersByUserID(id))
        return Response.ok("cart.jsp").build()
    }
    private fun verifyKeyOrResponseKey(key: String?,block:(Int)->Unit):Response =
        login.getIDByKey(key?.toInt())
            ?.let {
                block(it)
                getResponse(it)
            } ?:Response.status(Response.Status.BAD_REQUEST.statusCode,"未登入").build()


    @GET
    fun blockOrGet(@CookieParam(R.cookie.key) key:String?) = verifyKeyOrResponseKey(key) {}

    @POST
    fun submitCart(@CookieParam(R.cookie.key) key:String?) = verifyKeyOrResponseKey(key) {
        dao.submitCart(it)
    }

    @DELETE
    fun deletePreOrder(@FormParam("id") preorderId:String?,@CookieParam(R.cookie.key) key: String?) = verifyKeyOrResponseKey(key) {
        preorderId?.toInt()?.let(dao::deletePreOrder)
    }
    @PUT
    fun doChangeAmount(
        @CookieParam(R.cookie.key) key: String?,
        @Pattern(regexp = "[0-9]+")
        @FormParam("id")
        @NotNull @Valid id:String,
        @Pattern(regexp = "[0-9]+")
        @FormParam("amount")
        @NotNull @Valid amount:String,
    ) = verifyKeyOrResponseKey(key) {
        dao.getPreOrdersByID(id.toInt())?.run {
            count = amount.toInt()
            dao.changePreOrder(this)
        }
    }
}