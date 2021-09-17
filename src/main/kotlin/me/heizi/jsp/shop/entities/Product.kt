package me.heizi.jsp.shop.entities

import jakarta.persistence.*
import me.heizi.jsp.shop.annotations.Open

@Entity(name = "product")
@Table(name = "products")
@Open
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @Column var price:Double = 0.0,
    @Column var quantity: Int = 0,
    @Column var sold: Int = 0,
    @Column var image:String? = null,
    @Column var info:String? = null,
    @Column var city:String? = null,
    @Column(name = "create_time") var createTime:String? = null,
    @Column(name = "isHit") var hit: Int =0,
    @Column(name = "is_using") var using: Int =1,
    ) {

    @Column(nullable = false) lateinit var name:String

    @OneToOne
    lateinit var type: ProductType
}