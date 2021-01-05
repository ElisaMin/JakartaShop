package me.heizi.jsp.shopShit.dao

import me.heizi.jsp.shopShit.dao.entities.Product


interface Dao {


    fun getAllProducts():List<Product>
    fun findProductById(id:String):Product
    fun addToCart(productID: String,userId:String)


}