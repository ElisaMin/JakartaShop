package me.heizi.jsp.shop.services

import jakarta.inject.Inject
import jakarta.inject.Singleton
import me.heizi.jsp.shop.annotations.Open
import me.heizi.jsp.shop.dao.UserDao
import kotlin.random.Random

@Singleton
@Open
class LoginService {


    private val login = HashMap<Int,Int>()
    private val keyGen get() = System.currentTimeMillis().toInt() + Random.nextInt(100000)
    @Inject
    private lateinit var dao: UserDao

    /**
     * Get id by key
     *
     * @param key
     * @return null即非登入状态，否则返回ID。
     */
    fun getIDByKey(key: Int?):Int? = key?.let {
        login[it]?.let { id ->
            id.takeIf { dao.isUser(it) }
        }
    }

    fun isLogin(key:Int):Boolean {
        return login.keys.contains(key)
    }

    /**
     * 记录登入状态
     *
     * @param id 登入的ID
     */
    fun login(id: Int):Int  {
        var key = keyGen
        while (key in login.keys)
            key = keyGen
        login[key] = id
        return key
    }

}
