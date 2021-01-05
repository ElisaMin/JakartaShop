package me.heizi.jsp.shopShit.dao.entities

import jakarta.persistence.*

@Entity(name = "preOrders")
@Table(name="pre_orders")
open class PreOrder {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id = 0

    @Column open lateinit var name: String

    @Column(name="user_id") open var userId =  0
    @Column(name="product_id") open var productId =  0
    @Column(name="counts") open var count=  0
}