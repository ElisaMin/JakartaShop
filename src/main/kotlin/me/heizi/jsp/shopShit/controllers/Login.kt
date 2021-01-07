package me.heizi.jsp.shopShit.controllers

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
import me.heizi.jsp.shopShit.R
import me.heizi.jsp.shopShit.annotation.Open
import me.heizi.jsp.shopShit.dao.Dao
import me.heizi.jsp.shopShit.dao.PersistenceManager
import me.heizi.jsp.shopShit.dao.entities.User

@Path("login")
@Controller
@Open class Login {


    @GET
    @View("login.jsp")
    fun justResponse(@CookieParam(R.cookie.id) id:String?):Response {
        //如果用户存在则阻止本次
        return kotlin.runCatching {
            PersistenceManager.useWithResult {
                find(User::class.java,id).id
                //存在
                Response.status(Response.Status.BAD_REQUEST).entity("/").build()
            }
        }.getOrDefault(Response.ok().build())
    }

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
    @Inject private lateinit var dao:Dao
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
            //通知界面
            model.put("status",1)
            Response //响应良好并把ID放进用户浏览器
                .ok("login.jsp")
                .cookie(NewCookie( //添加cookie的Id
                    R.cookie.id,
                    dao.getIdByUsername(form.name)
                ))
                .build()
        }
        else -> {//其他错误
            model.put("status",-1)
            Response.status(Response.Status.FORBIDDEN).entity("login.jsp").build()
        }
    }
}