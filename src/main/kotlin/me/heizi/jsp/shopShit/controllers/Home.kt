package me.heizi.jsp.shopShit.controllers

import jakarta.inject.Inject
import jakarta.mvc.Controller
import jakarta.mvc.Models
import jakarta.mvc.View
import jakarta.ws.rs.CookieParam
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.core.Response
import me.heizi.jsp.shopShit.ApplicationScopeParameterSaver
import me.heizi.jsp.shopShit.R
import me.heizi.jsp.shopShit.annotations.Open
import me.heizi.jsp.shopShit.dao.Dao


@Path("/")
@Controller
@Open class Home {

    @Inject
    private lateinit var models: Models
    @Inject
    private lateinit var dao: Dao
    @Inject
    private lateinit var asps:ApplicationScopeParameterSaver

    /**
     * 主页判断用户响应对象
     *
     * @param id form cookie
     */
    @GET
    @View("home.jsp")
    fun doGet(@CookieParam(R.cookie.id) id:String?,):Response {
        models.put("list",dao.getHomeShowingProducts()) //list 首页
        return kotlin.runCatching { //可能转换失败 //id为空时isAdmin为空 否则是用户或者管理员
            models.put("isAdmin",id?.let { asps.isAdmin(it.toInt()) })
            Response.ok() //返回Ok响应
        }.getOrDefault(Response.status(
            Response.Status.BAD_REQUEST //返回错误选择的反应
        )).build()
    }
}