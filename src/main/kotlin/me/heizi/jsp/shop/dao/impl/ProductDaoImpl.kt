package me.heizi.jsp.shop.dao.impl

import me.heizi.jsp.shop.dao.PersistenceManager
import me.heizi.jsp.shop.dao.ProductDao
import me.heizi.jsp.shop.entities.Product
import me.heizi.jsp.shop.entities.ProductType

class ProductDaoImpl: ProductDao {


    /** 获取主页所需要展示的产品 */
    override fun getHomeShowingProducts(): List<Product> = PersistenceManager.useWithResult {
        createQuery("select p from product as p where p.using=1 ", Product::class.java).resultList
    }
    /** 查找商品*/
    override fun findProductById(id: Int): Product? = PersistenceManager.useWithResult {
        find(Product::class.java, id)
    }

    override fun getAllTypes(): List<ProductType> = PersistenceManager.useWithResult {
        createQuery("select t from productType t", ProductType::class.java).resultList
    }

    override fun findType(id: Int): ProductType = PersistenceManager.useWithResult {
        find(ProductType::class.java, id)
    }


    override fun add(product: Product) = PersistenceManager.useWithCommit {
        persist(product)
    }
}