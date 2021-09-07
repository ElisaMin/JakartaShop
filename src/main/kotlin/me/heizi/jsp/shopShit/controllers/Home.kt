package me.heizi.jsp.shopShit.controllers

import jakarta.inject.Inject
import jakarta.mvc.Controller
import jakarta.mvc.Models
import jakarta.mvc.View
import jakarta.ws.rs.CookieParam
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import me.heizi.jsp.shopShit.AdminChecker
import me.heizi.jsp.shopShit.LoginChecker
import me.heizi.jsp.shopShit.R
import me.heizi.jsp.shopShit.annotations.Open
import me.heizi.jsp.shopShit.dao.Dao
import me.heizi.jsp.shopShit.utils.set


@Path("/")
@Controller
@Open class Home {

    @Inject
    private lateinit var models: Models
    @Inject
    private lateinit var dao: Dao
    @Inject
    private lateinit var adminChecker: AdminChecker
    @Inject
    private lateinit var loginChecker:LoginChecker

    /**
     * 主页判断用户响应对象
     *
     * @param key form cookie
     */
    @GET
    @View("home.jsp")
    fun doGet(@CookieParam(R.cookie.key) key:String?)  {
        models["list"] = dao.getHomeShowingProducts()
        models["isAdmin"] = key?.toInt().let {loginChecker.getIDByKey(it) }.let { id->
            if (id == null) null else adminChecker.isAdmin(id)
        }
    }
}