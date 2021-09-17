package me.heizi.jsp.shop.entities

import jakarta.persistence.*
import me.heizi.jsp.shop.annotations.Open


/**
 * User
 * 用户表的JPA映射类
 */
@Entity(name = "user")
@Table(name = "users")
@Open data class User(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id var id:Int =0,
    @Column
    val credit:Int = 0,

    @Column(name = "isAdmin")
    var _isAdmin: Int =0,
    @Column
    var image:String? = null,
    @Column
    var gender:Int = 0

) {

    @Column(nullable = false)
    lateinit var name:String

    @Column(nullable = false)
    lateinit var password:String


    @Column(name = "phone",unique = true,nullable = false)
    lateinit var phone:String

    @Column(name = "last_login")
    lateinit var lastLogin:String

    @Column(name = "reg_time")
    lateinit var registerTime:String


    val isAdmin get() = _isAdmin==1
    var isMale
        get() = _isAdmin==1
        set(value) {
            gender = if (value) 1 else 0
        }
}
