package me.heizi.jsp.shopShit.controllers

import jakarta.inject.Inject
import jakarta.mvc.Controller
import jakarta.mvc.Models
import jakarta.mvc.View
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import me.heizi.jsp.shopShit.dao.Dao


@Path("/")
@Controller
open class Home {

    @Inject
    private lateinit var models: Models

    @Inject
    private lateinit var dao: Dao

    @GET
    @View("home.jsp")
    fun doGet() {
        models.put("list",dao.getAllProducts())
    }
}