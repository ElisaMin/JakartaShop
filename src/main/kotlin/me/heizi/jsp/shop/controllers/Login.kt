package me.heizi.jsp.shop.controllers

import jakarta.inject.Inject
import jakarta.mvc.Controller
import jakarta.mvc.Models
import jakarta.mvc.View
import jakarta.mvc.binding.BindingResult
import jakarta.mvc.binding.MvcBinding
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.ws.rs.*
import jakarta.ws.rs.core.NewCookie
import jakarta.ws.rs.core.Response
import me.heizi.jsp.shop.R
import me.heizi.jsp.shop.annotations.Open
import me.heizi.jsp.shop.dao.UserDao
import me.heizi.jsp.shop.filter.annotations.NotLoginOnly
import me.heizi.jsp.shop.services.LoginService
import me.heizi.jsp.shop.utils.set

@Path("login")
@Controller
@NotLoginOnly
@Open class Login {

    @Inject private lateinit var login:LoginService

    /**
     * Blocking
     *
     * @param id
     * @return
     */
    @GET
    @View("login.jsp")
    fun blocking(){}


    class Form {
        @MvcBinding
        @FormParam("username")
        @NotNull
        lateinit var name:String
        @MvcBinding
        @FormParam("password")
        @NotNull
        lateinit var pswd:String

    }
    @Inject private lateinit var dao:UserDao
    @Inject private lateinit var model:Models
    @Inject private lateinit var bindingResult:BindingResult


    /**
     * Verify 登入验证
     * @param form
     * @return
     */
    @POST
    fun verify(@Valid @BeanParam form:Form,):Response =  when {
        bindingResult.isFailed -> // form部分缺失
            Response //响应错误
                .status(Response.Status.BAD_REQUEST)
                .build()
        dao.verifyUser(form.name,form.pswd) -> {//密码正确
            model["status"] = 1
            Response
                .ok("login.jsp")
                .cookie(NewCookie(
                    R.cookie.key,
                    login.login(dao.getIdByUsername(form.name)).toString()
                ))
                .build()
        }
        else -> {//其他错误
            model.put("status",-1)
            Response.status(Response.Status.FORBIDDEN).entity("login.jsp").build()
        }
    }
    @GET
    @Path("exit")
    fun exit(): Response =
        Response.ok("退出成功").cookie(NewCookie(R.cookie.key,null)).build()

}