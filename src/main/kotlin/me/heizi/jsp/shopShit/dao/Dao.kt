package me.heizi.jsp.shopShit.dao

import me.heizi.jsp.shopShit.dao.entities.Product


interface Dao {


    fun getAllProducts():List<Product>
    fun findProductById(id:String):Product?
    fun addToCart(productID: String,userID:String)
    fun verifyUser(username:String,password:String):Boolean
    fun isAdmin(id: String):Boolean
    fun getIdByUsername(username:String):String

}