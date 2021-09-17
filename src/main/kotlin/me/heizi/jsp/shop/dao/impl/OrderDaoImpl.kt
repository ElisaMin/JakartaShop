package me.heizi.jsp.shop.dao.impl

import me.heizi.jsp.shop.dao.OrderDao
import me.heizi.jsp.shop.dao.PersistenceManager
import me.heizi.jsp.shop.entities.PreOrder
import me.heizi.jsp.shop.entities.Product
import me.heizi.jsp.shop.entities.User

/**
 * [OrderDao]
 */
class OrderDaoImpl: OrderDao {

    /**  添加到购物车*/
    override fun addToCart(productID: Int, userID: Int) {
        PersistenceManager.useWithCommit {
            persist(
                PreOrder().apply {
                    count = 1
                    product = find(Product::class.java, productID)
                    user = find(User::class.java, userID)
                }
            )
        }
    }
    /** 更改购物单 */
    override fun changePreOrder(preOrder: PreOrder) = PersistenceManager.useWithCommit {
        persist(preOrder)
    }

    /**通过id获取购物车 单*/
    override fun getPreOrdersByUserID(id: Int): List<PreOrder> = PersistenceManager.useWithResult {
        createQuery("select o from preOrders o where o.user.id = :uid", PreOrder::class.java)
            .setParameter("uid", id)
            .resultList
    }

    override fun getPreOrdersByID(id: Int): PreOrder? = PersistenceManager.useWithResult {
        find(PreOrder::class.java, id)
    }

    /**提交用户的购物车*/
    override fun submitCart(id: Int) {
        TODO("Not yet implemented")
    }
    /**删除购物车 单*/
    override fun deletePreOrder(id: Int) = PersistenceManager.useWithCommit {
        createQuery("delete from preOrders where id = :id")
            .setParameter("id", id)
            .executeUpdate()
        Unit
    }


}

