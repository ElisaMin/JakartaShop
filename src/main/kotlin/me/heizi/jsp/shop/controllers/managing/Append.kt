package me.heizi.jsp.shop.controllers.managing

import jakarta.inject.Inject
import jakarta.mvc.Controller
import jakarta.mvc.Models
import jakarta.mvc.View
import jakarta.mvc.binding.BindingResult
import jakarta.mvc.binding.MvcBinding
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.ws.rs.*
import me.heizi.jsp.shop.annotations.Open
import me.heizi.jsp.shop.dao.ProductDao
import me.heizi.jsp.shop.entities.Product
import me.heizi.jsp.shop.filter.annotations.AdminOnly
import me.heizi.jsp.shop.utils.convertISO88591toUTF8
import me.heizi.jsp.shop.utils.getFormattedTimeForNow
import me.heizi.jsp.shop.utils.set

@AdminOnly
@Path("/managing/append")
@Controller
@View("append.jsp")
@Open class Append {

    @Inject private lateinit var model: Models
    @Inject private lateinit var products: ProductDao

    @GET
    fun justResponse() {
        model["t"] = products.getAllTypes()

    }

    @Inject private lateinit var bindingResult: BindingResult

    @Open class Form {
        @NotNull(message = "typeID必须为数字") @MvcBinding
        @FormParam("tid") @Pattern(regexp = "[0-9]+")
        lateinit var tID:String

        @NotNull(message = "price不可为空") @MvcBinding
        @FormParam("prc") @Pattern(regexp = "[1-9]+")
        lateinit var price:String

        /** 图片ID*/
        @FormParam("pid")
        var pId:String?=null

        @FormParam("det")
        var detail:String?=null
        @FormParam("cti")
        var city:String?=null

        @NotNull @MvcBinding
        @FormParam("name")
        lateinit var name:String
        override fun toString(): String {
            return "Form(tID='$tID', price='$price', pId=$pId, detail=$detail, city=$city, name='$name')"
        }


    }


    @POST
    @Produces("text/html")
    @View("append.jsp")
    fun post(@Valid @BeanParam form:Form ) {
        //判断是否绑定成功
        if (bindingResult.isFailed)
            throw BadRequestException(bindingResult.allMessages.joinToString(",",prefix = "error!: "))
        println(form)
        //创建
        model["success"] = Product(
            price = form.price.toDouble(),
            quantity = 1,
            image = form.pId,
            info = form.detail?.convertISO88591toUTF8(),
            city = form.city?.convertISO88591toUTF8(),
            createTime = getFormattedTimeForNow()
        ).also {
            it.name = form.name.convertISO88591toUTF8()
            it.type = products.findType(form.tID.toInt())
            println(it)
        //添加到表里面
        }.runCatching { products.add(this) }.onFailure {
            throw InternalServerErrorException(it)
        }.isSuccess

    }
}

