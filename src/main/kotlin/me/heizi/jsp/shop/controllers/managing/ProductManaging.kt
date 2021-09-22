

package me.heizi.jsp.shop.controllers.managing

import jakarta.inject.Inject
import jakarta.mvc.Controller
import jakarta.mvc.Models
import jakarta.mvc.View
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Response
import me.heizi.jsp.shop.dao.ProductDao
import me.heizi.jsp.shop.filter.annotations.AdminOnly
import me.heizi.jsp.shop.utils.set

@Path("/managing/product/{page}")
@AdminOnly
@Controller
@Suppress("NAME_SHADOWING")
class ProductManaging {


    @Inject private lateinit var products:ProductDao
    @Inject private lateinit var models: Models

    @GET
    @View("product-managing.jsp")
    fun get(@PathParam("page") page:String) {
        val page = page.toIntOrNull()?.takeIf { it>=1 }
            ?: throw BadRequestException("页面不存在")
        models["products"] = products.getByPaged(page)
    }

    @PUT
    fun put(
        @FormParam("pid") pid:String,
        @FormParam("prc") prc:String?,
        @FormParam("qtt") qtt:String?,
        @FormParam("nme") nme:String?
    ):Response  {
        if (arrayOf(qtt,prc,nme).filterNotNull().isEmpty()) throw BadRequestException("Body为空")
        val product = pid.toIntOrNull()?.let (products::get)
            ?:throw NotFoundException("找不到商品")
        val diff = product.copy()
        prc?.let { u-> product.price = u.toDouble() }
        qtt?.let { u-> product.quantity = u.toInt() }
        nme?.let { u-> product.name = u }
        if (product==diff) throw BadRequestException("未修改") else products.change(product)
        return Response.ok().build()
    }
    @DELETE
    fun delete(@FormParam("id") id:Int?):Response {
        id
            ?.let (products::get)
            ?.let(products::delete)
            ?:throw NotFoundException("找不到商品")
        return  Response.ok().build()
    }
}