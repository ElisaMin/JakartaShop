package me.heizi.jsp.shopShit

import jakarta.inject.Inject
import jakarta.inject.Singleton
import me.heizi.jsp.shopShit.annotations.Open
import me.heizi.jsp.shopShit.dao.Dao
import kotlin.random.Random



@Singleton
@Open class AdminChecker {

    private val ramRepo = HashMap<Int,Boolean>()
    @Inject private lateinit var dao: Dao


    /**
     * Map查找id为空则往Map插Dao查找结果并返回
     */
    fun isAdmin(id:Int):Boolean = ramRepo[id]?: kotlin.runCatching {

        dao.isAdmin(id).also {
            ramRepo[id] = it
        }
    }.getOrDefault(false)

}

@Singleton
@Open class LoginChecker {


    private val login = HashMap<Int,Int>()

    @Inject private lateinit var dao: Dao

    fun getIDByKey(key: Int?):Int? = key?.let {
        login[it]?.let { id ->
            id.takeIf { dao.isUser(it) }
        }
    }
    private val keyGen get() = System.currentTimeMillis().toInt() + Random.nextInt(100000)

    fun login(id: Int):Int  {
        var key = keyGen
        while (key in login.keys)
            key = keyGen
        login[key] = id
        return key
    }

}
