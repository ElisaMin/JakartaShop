package me.heizi.jsp.shop.dao

import me.heizi.jsp.shop.entities.User

interface UserDao {
    /**验证用户的密码*/
    fun verifyUser(username:String,password:String):Boolean
    /**判断是否为管理员*/
    fun isAdmin(id: Int):Boolean
    /**判断是否为用户*/
    fun isUser(id: Int?):Boolean
    /**获取用户的ID*/
    fun getIdByUsername(username:String):Int
    /** 添加用户*/
    fun addUser(user: User)

}