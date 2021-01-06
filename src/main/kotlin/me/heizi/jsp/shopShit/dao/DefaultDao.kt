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
    override fun getAllProducts(): List<Product> = PersistenceManager.useWithResult {
        createQuery("select p from product as p",Product::class.java).resultList
    }


    override fun findProductById(id: String): Product? = PersistenceManager.useWithResult{
        kotlin.runCatching {
            find(Product::class.java,id)
        }.getOrNull()
    }

    override fun addToCart(productID: String, userID: String) {
        PersistenceManager.useWithCommit {
            this.persist(
                PreOrder().apply {
                    count = 1
                    productId = productID.toInt()
                    userId = userID.toInt()
                }
            )
        }
    }

    override fun verifyUser(username: String, password: String): Boolean = PersistenceManager.useWithResult {
        kotlin.runCatching {
            createQuery("select count(user) from user where name=:n and password =:p")
                .setParameter("p",password)
                .setParameter("u",username)
                .singleResult as Int

        }.getOrDefault(-1) == 1
    }

    override fun isAdmin(id: String): Boolean = PersistenceManager.useWithResult {
        find(User::class.java,id).isAdmin
    }

    override fun getIdByUsername(username: String): String = PersistenceManager.useWithResult {
        createQuery("select u.id from user u where u.name = :user ")
            .setParameter("user",username)
            .singleResult as String
    }
}