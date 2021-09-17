package me.heizi.jsp.shop.dao

import me.heizi.jsp.shop.entities.PreOrder

interface OrderDao {
    /**  添加到购物车*/
    fun addToCart(productID: Int,userID:Int)
    /**通过id获取购物车 单*/
    fun getPreOrdersByUserID(id: Int):List<PreOrder>
    /**获取购物车 单*/
    fun getPreOrdersByID(id: Int): PreOrder?
    /**提交用户的购物车*/
    fun submitCart(id:Int)
    /**删除购物车 单*/
    fun deletePreOrder(id:Int)
    /**修改购物单*/
    fun changePreOrder(preOrder: PreOrder)
}