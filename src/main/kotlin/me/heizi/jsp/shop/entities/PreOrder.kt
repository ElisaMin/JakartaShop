package me.heizi.jsp.shop.entities

import jakarta.persistence.*

@Entity(name = "preOrders")
@Table(name="pre_orders")
data class PreOrder(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @Column(name="counts") var count: Int =  0,
) {
    @OneToOne
    lateinit var user: User
    @OneToOne
    lateinit var product: Product
}