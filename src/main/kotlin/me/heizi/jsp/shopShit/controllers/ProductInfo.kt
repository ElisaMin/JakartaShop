package me.heizi.jsp.shopShit.controllers

import jakarta.enterprise.context.SessionScoped
import jakarta.inject.Inject
import jakarta.mvc.Controller
import jakarta.mvc.Models
import jakarta.mvc.View
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.UriInfo
import me.heizi.jsp.shopShit.LoginChecker
import me.heizi.jsp.shopShit.R
import me.heizi.jsp.shopShit.annotations.Open
import me.heizi.jsp.shopShit.dao.Dao



@Controller
@Path("/info")
@Open class ProductInfo {
    /**
     * Get id不存在时导航到/
     */
    @GET
    fun get() = Response.ok()
//        .entity(null)
//        .location(uriInfo.baseUri)
        .build()

    @SessionScoped
    @Inject private lateinit var models: Models
    @Inject private lateinit var dao:Dao
    @Inject private lateinit var loginChecker:LoginChecker
    //FIXME 这里炸了
    @Inject private lateinit var uriInfo: UriInfo


    /**
     * @状态:判断是否登入
     */
    @GET
    @Path("/info/{id}")
    @View("product-info.jsp")
    fun doGet(@CookieParam(R.cookie.key) key: String?, @PathParam("id") id:String?) {
        models.put("isLogin", loginChecker.getIDByKey(key?.toInt())!=null)
        models.put("p", id?.let { dao.findProductById(it) })
    }

    @POST
    @Path("/info/{id}")
    fun addCart(@PathParam("id") id:String?,@CookieParam(R.cookie.key) key:String?):Response {
        val user = loginChecker.getIDByKey(key?.toInt())
        if (id==null || user==null) {
            models.put("isAddedToCart",false)
            return Response.status(Response.Status.BAD_REQUEST).entity("product-info.jsp").build()
        }
        dao.addToCart(id,user.toString())
        models.put("isAddedToCart",true)
        return Response.ok("product-info.jsp").build()
    }

}