package me.heizi.jsp.shop.controllers

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
import me.heizi.jsp.shop.dao.UserDao
import me.heizi.jsp.shop.entities.User
import me.heizi.jsp.shop.filter.annotations.NotLoginOnly
import me.heizi.jsp.shop.utils.set

@Path("resign")
@Controller
@View("resign.jsp")
open class Resign {

    @Inject private lateinit var users: UserDao
    @Inject private lateinit var module: Models

    @GET @NotLoginOnly fun page(){}

    @POST
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
        }.let(users::addUser)

        module["isLoginSuccess"]=true
    }
}

@Path("adminShit")
@Controller
@View("resign.jsp")
class ResignForTest {
    @Inject private lateinit var users: UserDao
    @Inject private lateinit var module: Models

    @GET @NotLoginOnly fun page(){}

    @POST
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
            this._isAdmin = 1
        }.let(users::addUser)

        module["isLoginSuccess"]=true
    }
}