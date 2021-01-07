package me.heizi.jsp.shopShit.dao

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Named
import jakarta.inject.Singleton
import me.heizi.jsp.shopShit.dao.entities.PreOrder
import me.heizi.jsp.shopShit.dao.entities.Product
import me.heizi.jsp.shopShit.dao.entities.User


@Named("dao")
@Singleton
@ApplicationScoped
class DefaultDao:Dao {

    /** 获取主页所需要展示的产品 */
    override fun getHomeShowingProducts(): List<Product> = PersistenceManager.useWithResult {
        createQuery("select p from product as p ",Product::class.java).resultList
    }
    /** 查找商品*/
    override fun findProductById(id: String): Product? = PersistenceManager.useWithResult{
        kotlin.runCatching {
            find(Product::class.java,id)
        }.getOrNull()
    }
    /**  添加到购物车*/
    override fun addToCart(productID: String, userID: String) {
        PersistenceManager.useWithCommit {
            this.persist(
                PreOrder().apply {
                    count = 1
                    product = find(Product::class.java,productID)
                    user = find(User::class.java,userID)
                }
            )
        }
    }
    /**验证用户的密码*/
    override fun verifyUser(username: String, password: String): Boolean = PersistenceManager.useWithResult {
        kotlin.runCatching {
            createQuery("select count(user) from user where name=:n and password =:p")
                .setParameter("p",password)
                .setParameter("u",username)
                .singleResult as Int

        }.getOrDefault(-1) == 1
    }
    /**判断是否为管理员*/
    override fun isAdmin(id: Int): Boolean = PersistenceManager.useWithResult {
        find(User::class.java,id).isAdmin
    }
    /**判断是否为用户*/
    override fun isUser(id: Int?): Boolean = kotlin.runCatching cat@{
        PersistenceManager.useWithResult {
            id?.let {  find(User::class.java,it).id == id } ?: throw NullPointerException("id为空")
        }
    }.getOrDefault(false)

    override fun getIdByUsername(username: String): String = PersistenceManager.useWithResult {
        createQuery("select u.id from user u where u.name = :user ")
            .setParameter("user",username)
            .singleResult as String
    }
    /**通过id获取购物车 单*/
    override fun getPreOrdersByID(id: Int): List<PreOrder> {
        TODO("Not yet implemented")
    }
    /**提交用户的购物车*/
    override fun submitCart(id: Int) {
        TODO("Not yet implemented")
    }
    /**删除购物车 单*/
    override fun deletePreOrder(id: Int) {
        TODO("Not yet implemented")
    }
    /** */
}