package me.heizi.jsp.shopShit.dao

import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.EntityManager
import jakarta.persistence.Persistence


object PersistenceManager {

    private val entityManger by lazy {  Persistence.createEntityManagerFactory("default").createEntityManager() }
    fun useWithCommit(block:EntityManager.()->Unit) {
        entityManger.transaction.begin()
        entityManger.block()
        entityManger.transaction.commit()
    }
    fun <T> useWithResult(block:EntityManager.()->T):T = block(entityManger)




}