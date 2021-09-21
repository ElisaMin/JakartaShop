package me.heizi.jsp.shop.controllers.managing

import jakarta.inject.Inject
import jakarta.mvc.Controller
import jakarta.ws.rs.CookieParam
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.core.Response

import me.heizi.jsp.shop.R
import me.heizi.jsp.shop.services.LoginService

@Path("/fastLogin/{id}")
@Controller
@Deprecated("正式上线时就别用了!!!")
class LoginAdminForTest {
    @Inject private lateinit var login:LoginService
    @GET
    fun get(@CookieParam(R.cookie.key) key:Int?,@PathParam("id") id:Int):Response? = key?.let {

        login[it] = id
        Response.ok().build()
    }
}