package me.heizi.jsp.shop.dao.impl

import me.heizi.jsp.shop.dao.PersistenceManager
import me.heizi.jsp.shop.dao.PersistenceManager.useWithCommit
import me.heizi.jsp.shop.dao.PersistenceManager.useWithResult
import me.heizi.jsp.shop.dao.ProductDao
import me.heizi.jsp.shop.entities.Product
import me.heizi.jsp.shop.entities.ProductType

class ProductDaoImpl: ProductDao {

    override fun getByPaged(page: Int): List<Product> = useWithResult {
        createNativeQuery("select * from PRODUCTS where IS_USING = 1 order by id limit ?,? ",Product::class.java).apply {
            setParameter(1,(page-1)*30)
            setParameter(2,page*30)
        }.resultList as List<Product>? ?: emptyList()
    }

    override fun get(id: Int):Product? = useWithResult {
        find(Product::class.java,id)
    }


    override fun getHomeShowingProducts(): List<Product> = useWithResult {
        createQuery("select p from product as p where p.using=1 ", Product::class.java).resultList
    }

    override fun findProductById(id: Int): Product? = useWithResult {
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

    override fun change(product: Product) = add(product)
    override fun delete(product: Product) = useWithCommit {
        this.remove(product)
    }
}