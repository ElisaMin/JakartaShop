package me.heizi.jsp.shopShit.controllers

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
import jakarta.ws.rs.core.Response
import me.heizi.jsp.shopShit.annotations.Open
import me.heizi.jsp.shopShit.dao.Dao
import me.heizi.jsp.shopShit.dao.entities.Product
import me.heizi.jsp.shopShit.filter.annotations.AdminOnly
import me.heizi.jsp.shopShit.utils.getFormattedTimeForNow

@AdminOnly
@Path("/managing/append")
@Controller
@Open class Append {

    @Inject private lateinit var model: Models
    @Inject private lateinit var dao: Dao

    @GET
    @View("append.jsp")
    fun justResponse() {
        model.put("t",dao.getAllTypes())

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

        @NotNull @MvcBinding
        @FormParam("name")
        lateinit var name:String

    }


    @POST
    @Produces("text/html")
    fun post(@Valid @BeanParam form:Form ):Response {
        //判断是否绑定成功
        if (bindingResult.isFailed)
            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(bindingResult.allMessages)
                .build()
        //创建
        Product(
            price = form.price.toDouble(),
            quantity = 1,
            image = form.pId,
            info = form.detail,
            createTime = getFormattedTimeForNow()
        ).also {
            it.name = form.name
            it.type = dao.findType(form.tID.toInt())
        //添加到表里面
        }.let(dao::addProduct)
        //响应
        return Response.ok("""
            成功
            <script>
                delay(3000);
                document.window.href = "managing/"
            </script>
        """.trimIndent()).build()
    }
}

