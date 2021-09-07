package me.heizi.jsp.shopShit.dao

import me.heizi.jsp.shopShit.dao.entities.PreOrder
import me.heizi.jsp.shopShit.dao.entities.Product
import me.heizi.jsp.shopShit.dao.entities.ProductType
import me.heizi.jsp.shopShit.dao.entities.User
import java.io.InputStream
import java.io.OutputStream

/**
 * Dao  用于处理大部分的数据库交互请求
 */
interface Dao {
    /** 获取主页所需要展示的产品 */
    fun getHomeShowingProducts():List<Product>
    /** 查找商品*/
    fun findProductById(id:String):Product?
    /**  添加到购物车*/
    fun addToCart(productID: String,userID:String)
    /**验证用户的密码*/
    fun verifyUser(username:String,password:String):Boolean
    /**判断是否为管理员*/
    fun isAdmin(id: Int):Boolean
    /**判断是否为用户*/
    fun isUser(id: Int?):Boolean
    /**获取用户的ID*/
    fun getIdByUsername(username:String):Int
    /**通过id获取购物车 单*/
    fun getPreOrdersByUserID(id: Int):List<PreOrder>
    /**获取购物车 单*/
    fun getPreOrdersByID(id: Int):PreOrder?
    /**提交用户的购物车*/
    fun submitCart(id:Int)
    /**删除购物车 单*/
    fun deletePreOrder(id:Int)
    /**修改购物单*/
    fun changePreOrder(preOrder: PreOrder)
    /** 获取所有的Type */
    fun getAllTypes():List<ProductType>
    /** 通过ID获取Type */
    fun findType(id:Int):ProductType
    /** 添加图片*/
    fun insertImage(inputStream: InputStream,mime:String)
    /** 获取图片,通过ID */
    data class Image(val outputStream: OutputStream, val mime: String)
    fun getImageByID(id:Int):Image
    /** 添加商品*/
    fun addProduct(product: Product)
    /** 添加用户*/
    fun addUser(user: User)
    /** */
}