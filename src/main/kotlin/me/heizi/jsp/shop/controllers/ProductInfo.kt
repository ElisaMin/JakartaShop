package me.heizi.jsp.shop.controllers

import jakarta.enterprise.context.SessionScoped
import jakarta.inject.Inject
import jakarta.mvc.Controller
import jakarta.mvc.Models
import jakarta.mvc.View
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Context
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.UriInfo
import me.heizi.jsp.shop.R
import me.heizi.jsp.shop.annotations.Open
import me.heizi.jsp.shop.dao.OrderDao
import me.heizi.jsp.shop.dao.ProductDao
import me.heizi.jsp.shop.services.LoginService
import me.heizi.jsp.shop.utils.set


@Controller
@Path("/info")
@Open class ProductInfo {
    /**
     * Get id不存在时导航到/
     */
    @GET
    @Path("/")
    fun get(@Context uriInfo: UriInfo) = Response.status(Response.Status.BAD_REQUEST).build()

    @SessionScoped
    @Inject private lateinit var models: Models
    @Inject private lateinit var orders:OrderDao
    @Inject private lateinit var products:ProductDao
    @Inject private lateinit var loginService: LoginService


    /**
     * /product/{$id} find product info form database and show it
     * 404 if unfound
     *
     * @state isLogin : if login then show "buy it" btn else "login"
     */
    @GET
    @Path("/{id}")
    @View("product-info.jsp")
    fun doGet(@CookieParam(R.cookie.key) key: String?, @PathParam("id") id:String?) {
        models["p"] = id?.toIntOrNull()?.let { products.findProductById(it) }
            ?: throw NotFoundException()
        models["isLogin"] = (key?.toIntOrNull()?.let(loginService::isLogin)) == true
    }

    @POST
    @View("product-info.jsp")
    @Path("/{id}")
    fun addCart(
        @PathParam("id") id:String?,
        @CookieParam(R.cookie.key) key:String?
    ) {
        val user = loginService.getIDByKey(key?.toIntOrNull())
            ?: throw ForbiddenException("未登入")

        models["isAddedToCart"] = id?.toIntOrNull()?.let {
            orders.runCatching { addToCart(it,user) }.onFailure(::println).isSuccess
        } == true
    }

}