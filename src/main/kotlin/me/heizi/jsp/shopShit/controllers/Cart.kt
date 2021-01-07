package me.heizi.jsp.shopShit.controllers

import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.mvc.Controller
import jakarta.mvc.Models
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Response
import me.heizi.jsp.shopShit.ApplicationScopeParameterSaver
import me.heizi.jsp.shopShit.R
import me.heizi.jsp.shopShit.annotation.Open
import me.heizi.jsp.shopShit.dao.Dao

/**
 * Cart
 *
 * @GET 阻止用户不存在 存在选择用户的数据
 * @POST 把购物车内的产品移动到订单表内
 * @DELETE 删除购物车内的玩意
 * TODO :: PUT更新购物车的数量
 */
@Path("/cart")
@Controller
@Open class Cart {

    @Inject private lateinit var asps:ApplicationScopeParameterSaver
    @RequestScoped
    @Inject private lateinit var model:Models
    @Inject private lateinit var dao:Dao

    private fun getResponse(id:Int):Response {
        model.put("list",dao.getPreOrdersByID(id))
        return Response.ok("cart.jsp").build()
    }

    @GET
    fun blockOrGet(@CookieParam(R.cookie.id) id:String?):Response {
        val idi = id?.toInt()
        return if ( asps.isUser(idi)) {//存在
            getResponse(idi!!)
        }else {//不存在
            Response.status(Response.Status.BAD_REQUEST.statusCode,"未登入").build()
        }
    }
    @POST
    fun submitCart(@CookieParam(R.cookie.id) id:String?):Response?
    = id?.toInt()?.let {
        dao.submitCart(it)
        getResponse(it)
    }

    @DELETE
    fun deletePreOrder(@FormParam("id") id:String?,@CookieParam(R.cookie.id) user: String?):Response? {
        id?.toInt()?.let {
            dao.deletePreOrder(it)
        }
        return user?.toInt()?.let(::getResponse)
    }
    @PUT
    fun doChangeAmount(
        @Pattern(regexp = "[0-9]+")
        @FormParam("id")
        @NotNull @Valid id:String,
        @Pattern(regexp = "[0-9]+")
        @FormParam("amount")
        @NotNull @Valid amount:String,
    ) {
        TODO("懒得实现")
    }
}