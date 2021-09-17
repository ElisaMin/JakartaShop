package me.heizi.jsp.shop.dao.impl

import me.heizi.jsp.shop.dao.PersistenceManager
import me.heizi.jsp.shop.dao.UserDao
import me.heizi.jsp.shop.entities.User

class UserDaoImpl: UserDao {
    /**验证用户的密码*/
    override fun verifyUser(username: String, password: String): Boolean = PersistenceManager.useWithResult {
        kotlin.runCatching {
            createQuery("select count(u) from user as u where u.name=:n and u.password =:p", Long::class.java)
                .setParameter("p", password)
                .setParameter("n", username)
                .singleResult
        }.onFailure(::println).getOrDefault(-1L) == 1L
    }
    /**判断是否为管理员*/
    override fun isAdmin(id: Int): Boolean = PersistenceManager.useWithResult {
        find(User::class.java, id).isAdmin
    }
    /**判断是否为用户*/
    override fun isUser(id: Int?): Boolean = kotlin.runCatching cat@{
        PersistenceManager.useWithResult {
            id?.let { find(User::class.java, it).id == id } ?: throw NullPointerException("id为空")
        }
    }.getOrDefault(false)

    override fun getIdByUsername(username: String): Int = PersistenceManager.useWithResult {
        createQuery("select u.id from user u where u.name = :user ")
            .setParameter("user", username)
            .singleResult as Int
    }
    override fun addUser(user: User) = PersistenceManager.useWithCommit {
        persist(user)
        user
    }.let {
        println("user found:"+ PersistenceManager.useWithResult {
            find(User::class.java, it.id)
        })

    }
}