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

@Path("resign")
@Controller
@Open class Resign {
//    init {
//        TODO("最后再做")
//    }

    @View("resign.jsp")
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
        @FormParam("forTest")
        @Valid forTest:String?,
    ) {
        User().apply {
            name = names
            this.phone = phone
            this.password = password
            this._isAdmin = if (forTest == "admin") 1 else 0
        }.let(dao::addUser)
        module.put("responseMessage","""|注册成功！！！！！
            |<script>document.querySelectorAll("input").forEach((i)=>{i.disabled = true})</script>
        """.trimMargin())
    }
}