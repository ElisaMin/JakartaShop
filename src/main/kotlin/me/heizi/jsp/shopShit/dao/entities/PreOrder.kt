package me.heizi.jsp.shopShit.dao.entities

import jakarta.persistence.*
import me.heizi.jsp.shopShit.annotation.Open

@Entity(name = "preOrders")
@Table(name="pre_orders")
@Open data class PreOrder(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @Column(name="user_id") var userId: Int =  0,
    @Column(name="product_id") var productId: Int =  0,
    @Column(name="counts") var count: Int =  0,
) {
    @Column lateinit var name: String
}