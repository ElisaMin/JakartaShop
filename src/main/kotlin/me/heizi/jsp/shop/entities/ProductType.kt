package me.heizi.jsp.shop.entities

import jakarta.persistence.*
import me.heizi.jsp.shop.annotations.Open

@Entity(name = "productType")
@Table(name = "product_type")
@Open data class ProductType (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @Column
    var name:String? = null
)