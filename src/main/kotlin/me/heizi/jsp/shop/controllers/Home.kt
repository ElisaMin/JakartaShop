package me.heizi.jsp.shop.controllers

import jakarta.inject.Inject
import jakarta.mvc.Controller
import jakarta.mvc.Models
import jakarta.mvc.View
import jakarta.ws.rs.CookieParam
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import me.heizi.jsp.shop.R
import me.heizi.jsp.shop.annotations.Open
import me.heizi.jsp.shop.dao.ProductDao
import me.heizi.jsp.shop.services.AdminService
import me.heizi.jsp.shop.services.LoginService
import me.heizi.jsp.shop.utils.set


@Path("/")
@Controller
@Open class Home {

    @Inject private lateinit var models: Models
    @Inject private lateinit var products: ProductDao
    @Inject private lateinit var admin: AdminService
    @Inject private lateinit var login:LoginService

    /**
     * 主页判断用户响应对象
     *
     * @param key form cookie [LoginService]
     */
    @GET
    @View("home.jsp")
    fun doGet(@CookieParam(R.cookie.key) key:String?)  {
        models["list"] = products.getHomeShowingProducts()
        models["isAdmin"] = key?.toInt().let {login.getIDByKey(it) }.let { id->
            if (id == null) null else admin.isAdmin(id)
        }
    }
}