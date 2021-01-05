package me.heizi.jsp.shopShit.dao

import me.heizi.jsp.shopShit.dao.entities.PreOrder
import me.heizi.jsp.shopShit.dao.entities.Product


class DefaultDao:Dao {
    override fun getAllProducts(): List<Product> = PersistenceManager.useWithResult {
        createQuery("select product from product ",Product::class.java).resultList
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
}