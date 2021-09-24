package me.heizi.jsp.shop.dao.impl

import me.heizi.jsp.shop.dao.OrderDao
import me.heizi.jsp.shop.dao.PersistenceManager
import me.heizi.jsp.shop.dao.PersistenceManager.useWithCommit
import me.heizi.jsp.shop.dao.PersistenceManager.useWithResult
import me.heizi.jsp.shop.entities.*
import me.heizi.jsp.shop.utils.format
import me.heizi.jsp.shop.utils.nowFormatted
import java.util.*

/**
 * [OrderDao]
 */
class OrderDaoImpl: OrderDao {

    /**  添加到购物车*/
    override fun addToCart(productID: Int, userID: Int) {
        useWithCommit {
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

    override fun getByPage(page: Int): List<Order> = useWithResult {
        createNativeQuery("select * from orders order by id limit ?,? ",Order::class.java).apply {
            setParameter(1,(page-1)*10)
            setParameter(2,page*10)
        }.resultList as List<Order>? ?: emptyList()
    }

    override fun find(id: Int): Order? = useWithResult {
        runCatching { 
            find(Order::class.java,id)
        }.getOrNull()
    }

    override fun findSub(id: Int): SubOrder? = useWithResult {
        runCatching {
            find(SubOrder::class.java,id)
        }.getOrNull()
    }

    override fun remove(order: Order) = useWithCommit {
        this.remove(order)
    }

    override fun remove(subOrder: SubOrder)= useWithCommit {
        this.remove(subOrder)
    }

    override fun change(order: Order) = useWithCommit {
        this.persist(order)
    }

    override fun change(subOrder: SubOrder) = useWithCommit {
        this.persist(subOrder)
    }

    /**提交用户的购物车*/
    override fun submitCart(userID: Int) {
        val time = Date()
        val nowFormatted = time.format()

        //create new order
        val orderId = useWithCommit {
//            Order(
//                time=time
//            ).apply {
//                user = find(User::class.java, userID)
//            }.let(this::persist)
            createNativeQuery("insert into ORDERS(GENERATE_TIME,USER_ID) values ( ?,?)").run {
                setParameter(1,time)
                setParameter(2,userID)
                executeUpdate()
            }
            val orderId = createNativeQuery("select id from ORDERS where GENERATE_TIME = ?").run {
                setParameter(1, time)
                (singleResult as Number).toInt()
            }
            //preorder to suborder
            createNativeQuery("insert into SUB_ORDERS(order_id, product_id, amount) SELECT ?,PRODUCT_ID,COUNTS from PRE_ORDERS").run {
                setParameter(1,orderId)
                executeUpdate()
            }.let {
                if (it<=0) throw IllegalStateException()
                else {

                }
            }
        }

//        useWithResult {
//
//        }
    }

    /**通过id获取购物车 单*/
    override fun getPreOrdersByUserID(id: Int): List<PreOrder> = useWithResult {
        createQuery("select o from preOrders o where o.user.id = :uid", PreOrder::class.java)
            .setParameter("uid", id)
            .resultList
    }

    override fun getPreOrdersByID(id: Int): PreOrder? = PersistenceManager.useWithResult {
        find(PreOrder::class.java, id)
    }
    /**删除购物车 单*/
    override fun deletePreOrder(id: Int) = PersistenceManager.useWithCommit {
        createQuery("delete from preOrders where id = :id")
            .setParameter("id", id)
            .executeUpdate()
        Unit
    }


}

