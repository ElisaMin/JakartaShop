package me.heizi.jsp.shopShit

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.inject.Singleton
import me.heizi.jsp.shopShit.annotation.Open
import me.heizi.jsp.shopShit.dao.Dao

@ApplicationScoped
@Singleton
@Open class ApplicationScopeParameterSaver {
    @Inject private lateinit var dao: Dao
    private val isAdmin = HashMap<Int,Boolean>()
    /**
     * Map查找id为空则往Map插Dao查找结果并返回
     */
    fun isAdmin(id:Int):Boolean = isAdmin[id]?: kotlin.run {
        dao.isAdmin(id).also {
            isAdmin[id] = it
        }
    }
}