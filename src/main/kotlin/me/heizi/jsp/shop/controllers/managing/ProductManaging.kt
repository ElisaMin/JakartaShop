

package me.heizi.jsp.shop.controllers.managing

import jakarta.inject.Inject
import jakarta.mvc.Controller
import jakarta.mvc.Models
import jakarta.mvc.View
import jakarta.ws.rs.*
import me.heizi.jsp.shop.dao.ProductDao
import me.heizi.jsp.shop.utils.set

@Path("/managing/product/{page}")
//@AdminOnly
@Controller
@Suppress("NAME_SHADOWING")
class ProductManaging {


    @Inject private lateinit var products:ProductDao
    @Inject private lateinit var models: Models

    @GET
    @View("product-managing.jsp")
    fun get(@PathParam("page") page:String) {
        val page = page.toInt().takeIf { it>=1 }
            ?: throw BadRequestException("页面不存在")
        models["products"] = products.getByPaged(page)
    }
    @PUT
    fun put(
        body:Map<String,String>,
    )  {
        if (body.size <=1) throw BadRequestException("Body为空")
        val product = body["pid"]?.toIntOrNull()?.let (products::get)
            ?:throw NotFoundException("找不到商品")
        var changed = false

        body.forEach { (t, u) ->
            if (t!="pid") try {
                when(t) {
                    "prc" -> {
                        product.price = u.toDouble()
                        changed = true
                    }
                    "qtt" -> {
                        product.quantity = u.toInt()
                        changed = true
                    }
                }
            } catch (e:IllegalArgumentException) {
                throw BadRequestException(e)
            }
        }
        if (!changed) throw BadRequestException("未修改") else products.change(product)
    }
    @PUT
    fun put(
        @FormParam("pid") pid:String,
        @FormParam("prc") prc:String?,
        @FormParam("qtt") qtt:String?,
        @FormParam("nme") nme:String?
    )  {

        if (arrayOf(qtt,prc,nme).filter { it==null}.size>=2) throw BadRequestException("Body为空")
        val product = pid.toIntOrNull()?.let (products::get)
            ?:throw NotFoundException("找不到商品")
        val diff = product.copy()
        prc?.let { u-> product.price = u.toDouble() }
        qtt?.let { u-> product.quantity = u.toInt() }
        nme?.let { u-> product.name = u }
        if (product==diff) throw BadRequestException("未修改") else products.change(product)
    }
    @DELETE
    fun delete(@CookieParam("id") id:String?) {
        id  ?.toIntOrNull()
            ?.let (products::get)
            ?.let(products::delete)
            ?:throw NotFoundException("找不到商品")
    }
}