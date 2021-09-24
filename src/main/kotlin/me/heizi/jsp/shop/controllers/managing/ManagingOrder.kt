package me.heizi.jsp.shop.controllers.managing

import jakarta.inject.Inject
import jakarta.mvc.Controller
import jakarta.mvc.Models
import jakarta.mvc.View
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Response
import me.heizi.jsp.shop.dao.OrderDao
import me.heizi.jsp.shop.entities.Order
import me.heizi.jsp.shop.entities.SubOrder
import me.heizi.jsp.shop.utils.set

@Controller
@Path("/managing/order/")
class ManagingOrder {


    @GET
    fun emptyGet() = Response.ok().build()
    @Inject private lateinit var model: Models
    @Inject private lateinit var orders:OrderDao

    @GET
    @View("order.jsp")
    @Path("/{page}")
    fun getByPage(@PathParam("page") page:Int){
        val list = orders.getByPage(page)
        model["orders"] = list
        println(list.joinToString { it.toString() })
    }

    fun <T> checkNotExistOrKeep(id:Int?,block:(Order)->T):T {
        return id?.let(orders::find)?.let(block)?: throw NotFoundException()
    }
    fun <T> checkNotExistOrKeepSub(id:Int?,block:(SubOrder)->T):T {
        return id?.let(orders::findSub)?.let(block)?: throw NotFoundException()
    }

    @DELETE
    @Path("order/{id}")
    fun deleteOrder(@PathParam("id") orderID:Int?): Response = checkNotExistOrKeep(orderID) {
        orders.remove(it)
        Response.ok().build()
    }
    @PUT
    @Path("order/{id}")
    fun doneOrder(@PathParam("id") orderID:Int?): Response = checkNotExistOrKeep(orderID) {
        it.isDone = false
        orders.change(it)
        Response.ok().build()
    }

    @DELETE
    @Path("sub/{id}")
    fun deleteSubOrder(@PathParam("id") orderID:Int?): Response = checkNotExistOrKeepSub(orderID) {
        orders.remove(it)
        Response.ok().build()
    }
    @PUT
    @Path("sub/{id}")
    fun doSubOrderChangeAmount(@PathParam("id") orderID:Int?, @FormParam("amt") amount:Int?) = amount?.let { amount->
        checkNotExistOrKeepSub(orderID) {
            it.amount = amount
            orders.change(it)
            Response.ok().build()
        }
    }?: throw BadRequestException("Amount Null")
}