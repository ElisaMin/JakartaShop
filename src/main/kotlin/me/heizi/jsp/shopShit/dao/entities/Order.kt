package me.heizi.jsp.shopShit.dao.entities

import jakarta.persistence.*

@Entity
@Table(name = "orders")
open class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id = 0

    @Column(name="user_id") open var userId =  0
    @Column(name="product_id") open var productId =  0

    @Column(name = "generate_time") open lateinit var time:String

    @Column open var amount =  0
    @Column(name = "comment") open lateinit var comment:String
    @Column(name = "comment_generate_time") open lateinit var timeComment:String

}