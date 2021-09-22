package me.heizi.jsp.shop.entities

import jakarta.persistence.*

@Entity
@Table(name = "sub_orders")
data class SubOrder(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @Column var amount : Int = 0
) {
    @OneToOne lateinit var order: Order
    @OneToOne lateinit var product: Product
    val price get() = product.price*amount
}