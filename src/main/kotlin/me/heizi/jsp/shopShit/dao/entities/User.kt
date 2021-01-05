package me.heizi.jsp.shopShit.dao.entities

import jakarta.persistence.*


/**
 * User
 * 用户表的JPA映射类
 */
@Entity(name = "user")
@Table(name = "users")
open class User {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id open var id:Int =0

    @Column(nullable = false)
    open lateinit var name:String

    @Column(nullable = false)
    open lateinit var password:String

    @Column
    open var gender:Int = 0

    @Column(name = "phone",unique = true,nullable = false)
    open lateinit var phone:String

    @Column
    open var image:String? = null

    @Column
    open val credit:Int = 0

    @Column(name = "isAdmin")
    open var _isAdmin=0

    @Column(name = "last_login")
    open lateinit var lastLogin:String

    @Column(name = "reg_time")
    open lateinit var registerTime:String


    val isAdmin get() = _isAdmin==1
    var isMale
        get() = _isAdmin==1
        set(value) {
            gender = if (value) 1 else 0
        }
}
