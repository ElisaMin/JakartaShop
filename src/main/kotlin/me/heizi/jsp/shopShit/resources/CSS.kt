package me.heizi.jsp.shopShit.resources

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.core.Response
import me.heizi.jsp.shopShit.annotations.Open
import org.apache.ibatis.io.Resources


@Path("/css/{resource}")
@Open class CSS {
    @GET
    fun getResources(@PathParam("resource") path:String): Response {
        return kotlin.runCatching {
            Response.ok(Resources.getResourceAsStream("/css/$path")).build()
        }.getOrDefault(Response.status(Response.Status.NOT_FOUND).build())

    }
}
