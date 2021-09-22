package me.heizi.jsp.shop.dao

import me.heizi.jsp.shop.entities.Order
import me.heizi.jsp.shop.entities.PreOrder
import me.heizi.jsp.shop.entities.SubOrder

interface OrderDao {
    /**  添加到购物车*/
    fun addToCart(productID: Int,userID:Int)
    /**通过id获取购物车 单*/
    fun getPreOrdersByUserID(id: Int):List<PreOrder>
    /**获取购物车 单*/
    fun getPreOrdersByID(id: Int): PreOrder?
    /**提交用户的购物车*/
    fun submitCart(userID:Int)
    /**删除购物车 单*/
    fun deletePreOrder(id:Int)
    /**修改购物单*/
    fun changePreOrder(preOrder: PreOrder)

    /**
     * 分页获取[Order]
     *
     * @param page 第几页 >0
     * @return empty list if over-page
     */
    fun getByPage(page:Int):List<Order>

    /**
     * 通过id查找[Order]
     *
     * @param id Order的ID
     * @return nullable[Order]
     */
    fun find(id:Int):Order?

    /**
     * 通过id查找[SubOrder]
     *
     * @param id Order的ID
     * @return nullable[SubOrder]
     */
    fun findSub(id:Int):SubOrder?

    /**
     * 删除订单
     *
     * @param order
     */
    fun remove(order: Order)

    /**
     * 删除订单商品
     *
     * @param subOrder
     */
    fun remove(subOrder: SubOrder)

    /**
     * 修改订单内容
     *
     * @param order
     */
    fun change(order: Order)

    /**
     * 修改子订单内容
     *
     * @param subOrder
     */
    fun change(subOrder: SubOrder)
}