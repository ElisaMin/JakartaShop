package me.heizi.jsp.shop.services

import jakarta.inject.Inject
import jakarta.inject.Singleton
import me.heizi.jsp.shop.annotations.Open
import me.heizi.jsp.shop.dao.UserDao


@Singleton
@Open class AdminService {

    private val ramRepo = HashMap<Int,Boolean>()
    @Inject private lateinit var dao: UserDao


    /**
     * Map查找id为空则往Map插Dao查找结果并返回
     */
    fun isAdmin(id:Int):Boolean = ramRepo[id]?: kotlin.runCatching {

        dao.isAdmin(id).also {
            ramRepo[id] = it
        }
    }.getOrDefault(false)

}

