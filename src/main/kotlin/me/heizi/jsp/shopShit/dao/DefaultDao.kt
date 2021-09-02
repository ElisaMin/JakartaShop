package me.heizi.jsp.shopShit.dao

import jakarta.inject.Named
import jakarta.inject.Singleton
import me.heizi.jsp.shopShit.dao.PersistenceManager.useWithCommit
import me.heizi.jsp.shopShit.dao.PersistenceManager.useWithResult
import me.heizi.jsp.shopShit.dao.entities.PreOrder
import me.heizi.jsp.shopShit.dao.entities.Product
import me.heizi.jsp.shopShit.dao.entities.ProductType
import me.heizi.jsp.shopShit.dao.entities.User
import java.io.InputStream

/**
 * Dao 实现
 */
@Named("dao")
@Singleton
class DefaultDao:Dao {

    /** 获取主页所需要展示的产品 */
    override fun getHomeShowingProducts(): List<Product> =useWithResult {
        createQuery("select p from product as p ",Product::class.java).resultList
    }
    /** 查找商品*/
    override fun findProductById(id: String): Product? =useWithResult{
        kotlin.runCatching {
            find(Product::class.java,id)
        }.getOrNull()
    }
    /**  添加到购物车*/
    override fun addToCart(productID: String, userID: String) {
       useWithCommit {
            persist(
                PreOrder().apply {
                    count = 1
                    product = find(Product::class.java,productID)
                    user = find(User::class.java,userID)
                }
            )
        }
    }
    /**验证用户的密码*/
    override fun verifyUser(username: String, password: String): Boolean =useWithResult {
        kotlin.runCatching {
            createQuery("select count(user) from user where name=:n and password =:p")
                .setParameter("p",password)
                .setParameter("u",username)
                .singleResult as Int

        }.getOrDefault(-1) == 1
    }
    /**判断是否为管理员*/
    override fun isAdmin(id: Int): Boolean =useWithResult {
        find(User::class.java,id).isAdmin
    }
    /**判断是否为用户*/
    override fun isUser(id: Int?): Boolean = kotlin.runCatching cat@{
       useWithResult {
            id?.let {  find(User::class.java,it).id == id } ?: throw NullPointerException("id为空")
        }
    }.getOrDefault(false)

    override fun getIdByUsername(username: String): String =useWithResult {
        createQuery("select u.id from user u where u.name = :user ")
            .setParameter("user",username)
            .singleResult as String
    }
    /**通过id获取购物车 单*/
    override fun getPreOrdersByID(id: Int): List<PreOrder> = useWithResult{
        createQuery("select o from preOrders o where o.user.id = :uid",PreOrder::class.java)
            .setParameter("uid",id)
            .resultList
    }
    /**提交用户的购物车*/
    override fun submitCart(id: Int) {
        TODO("Not yet implemented")
    }
    /**删除购物车 单*/
    override fun deletePreOrder(id: Int) = useWithCommit {
        createQuery("delete from preOrders where id = :id")
            .setParameter("id",id)
            .executeUpdate()
    }

    override fun getAllTypes(): List<ProductType> =useWithResult {
        createQuery("select t from productType t",ProductType::class.java).resultList
    }

    override fun findType(id: Int): ProductType =useWithResult {
        find(ProductType::class.java,id)
    }

    override fun insertImage(inputStream: InputStream, mime: String) {
        TODO("Not yet implemented")
    }

    override fun getImageByID(id: Int): Dao.Image {
        TODO("Not yet implemented")
    }

    override fun addProduct(product: Product) = useWithCommit{
        persist(product)
    }

    override fun addUser(user: User) = useWithCommit {
        persist(user)
    }
    /** */
}