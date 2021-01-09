package me.heizi.jsp.shopShit

import jakarta.inject.Inject
import jakarta.inject.Singleton
import me.heizi.jsp.shopShit.annotations.Open
import me.heizi.jsp.shopShit.dao.Dao


@Singleton
@Open class ApplicationScopeParameterSaver {

    @Inject private lateinit var dao: Dao

    private val isAdmin = HashMap<Int,Boolean>()
    /**
     * Map查找id为空则往Map插Dao查找结果并返回
     */
    fun isAdmin(id:Int):Boolean = isAdmin[id]?: kotlin.runCatching {
       dao.isAdmin(id).also {
            isAdmin[id] = it
        }
    }.getOrDefault(false)

    private val isUser = HashMap<Int,Boolean>()

    fun isUser(id: Int?) =  id?.let {
        isUser[it] ?: dao.isUser(it).also { b ->
            isUser[it] = b
        }
    } == true

}