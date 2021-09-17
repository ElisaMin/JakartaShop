package me.heizi.jsp.shop.controllers.managing

import jakarta.mvc.Controller
import jakarta.mvc.View
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import me.heizi.jsp.shop.filter.annotations.AdminOnly


@Controller
@AdminOnly
@Path("managing/")
@View("managing-home.jsp")
class ManagingHome {
    @GET
    fun view() {}
}