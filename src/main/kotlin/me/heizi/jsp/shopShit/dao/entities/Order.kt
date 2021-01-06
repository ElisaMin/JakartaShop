package me.heizi.jsp.shopShit.dao.entities

import jakarta.persistence.*
import me.heizi.jsp.shopShit.annotation.Open

@Entity
@Table(name = "orders")
@Open data class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @Column(name="user_id") var userId: Int =  0,
    @Column(name="product_id") var productId: Int =  0,
    @Column var amount: Int =  0
) {
    @Column(name = "generate_time") lateinit var time:String
    @Column(name = "comment") lateinit var comment:String
    @Column(name = "comment_generate_time") lateinit var timeComment:String

}