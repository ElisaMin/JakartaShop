package me.heizi.jsp.shopShit.controllers

import jakarta.inject.Inject
import jakarta.mvc.Controller
import jakarta.mvc.Models
import jakarta.mvc.View
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.ws.rs.FormParam
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import me.heizi.jsp.shopShit.annotations.Open
import me.heizi.jsp.shopShit.dao.Dao
import me.heizi.jsp.shopShit.dao.entities.User
import me.heizi.jsp.shopShit.filter.annotations.NotLoginOnly
import me.heizi.jsp.shopShit.utils.set

@Path("resign")
@Controller
@Open class Resign {
//    init {
//        TODO("最后再做")
//    }

    @View("resign.jsp")
    @NotLoginOnly
    @GET fun page(){}

   @Inject private lateinit var dao: Dao
   @Inject private lateinit var module: Models

    @POST
    @View("resign.jsp")
    fun resign(
        @FormParam("names")
        @NotNull @Valid names:String,
        @FormParam("phone")
        @NotNull @Valid phone:String,
        @FormParam("password")
        @NotNull @Valid password:String,
    ) {
        User().apply {
            name = names
            this.phone = phone
            this.password = password
            this._isAdmin = 0
            println(this)
        }.let(dao::addUser)
        module.put("isGoToHome",true)
        module.put("responseMessage","""|注册成功！！！！！
        """.trimMargin())
    }


    @View("resign.jsp")
    @NotLoginOnly
    @Path("admin")
    @GET fun admin(){}
    @POST
    @Path("admin")
    @View("resign.jsp")
    fun resignAdmin(
        @FormParam("names")
        @NotNull @Valid names:String,
        @FormParam("phone")
        @NotNull @Valid phone:String,
        @FormParam("password")
        @NotNull @Valid password:String,
    ) {
        User().apply {
            name = names
            this.phone = phone
            this.password = password
            this._isAdmin = 1
            println(this)
        }.let(dao::addUser)

        module.put("isGoToHome",true)
        module["responseMessage"]=
            """|注册成功！！！！！
        """.trimMargin()
    }

}