package me.heizi.jsp.shop.dao

import me.heizi.jsp.shop.entities.Product
import me.heizi.jsp.shop.entities.ProductType


interface ProductDao {
    /** 获取主页所需要展示的产品 */
    fun getHomeShowingProducts():List<Product>
    /** 查找商品*/
    fun findProductById(id:Int):Product?
    /** 获取所有的Type */
    fun getAllTypes():List<ProductType>
    /** 通过ID获取Type */
    fun findType(id:Int): ProductType
    /** 添加商品*/
    fun add(product: Product)
}
