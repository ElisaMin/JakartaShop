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
import jakarta.ws.rs.core.Response
import me.heizi.jsp.shopShit.dao.Dao

@Path("/info/{id}")
@Controller
open class ProductInfo {

    open class Form{
        @MvcBinding
        @NotNull
        @FormParam("id")
        open lateinit var id:String
        @MvcBinding
        @NotNull
        @FormParam("user_id")
        open lateinit var userId:String

    }

    @Inject private lateinit var models: Models
    @Inject private lateinit var dao:Dao
    @Inject private lateinit var bindingResult: BindingResult

    @GET
    @View("product-info.jsp")
    fun doGet(@PathParam("id") id:String?) {
        models.put("p", id?.let { dao.findProductById(it) })
    }

    @POST
    fun addCart(@Valid @BeanParam form:Form):Response {
        if (bindingResult.isFailed) {
            models.put("status",-2)
            Response.status(Response.Status.BAD_REQUEST).entity("product-info.jsp").build()
        }
        dao.addToCart(form.id,form.id)
        models.put("status",1)
        return Response.ok("product-info.jsp").build()
    }

}