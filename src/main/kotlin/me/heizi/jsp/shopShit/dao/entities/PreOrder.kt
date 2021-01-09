package me.heizi.jsp.shopShit.dao.entities

import jakarta.persistence.*
import me.heizi.jsp.shopShit.annotations.Open

@Entity(name = "preOrders")
@Table(name="pre_orders")
@Open data class PreOrder(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @Column(name="counts") var count: Int =  0,
) {
    @OneToOne
    lateinit var user: User
    @OneToOne
    lateinit var product: Product

    @Column lateinit var name: String
}