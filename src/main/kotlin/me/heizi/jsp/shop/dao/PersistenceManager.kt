package me.heizi.jsp.shop.dao

import jakarta.persistence.EntityManager
import jakarta.persistence.Persistence


object PersistenceManager {

    private val entityManger by lazy {  Persistence.createEntityManagerFactory("default").createEntityManager() }
    fun <T> useWithCommit(block:EntityManager.()->T):T {
        entityManger.transaction.begin()
        val result = entityManger.block()
        entityManger.transaction.commit()
        return result
    }
    fun <T> useWithResult(block:EntityManager.()->T):T = block(entityManger)




}