package me.heizi.jsp.shopShit.dao

import me.heizi.jsp.shopShit.dao.entities.PreOrder
import me.heizi.jsp.shopShit.dao.entities.Product


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
    fun getIdByUsername(username:String):String
    /**通过id获取购物车 单*/
    fun getPreOrdersByID(id: Int):List<PreOrder>
    /**提交用户的购物车*/
    fun submitCart(id:Int)
    /**删除购物车 单*/
    fun deletePreOrder(id:Int)
}