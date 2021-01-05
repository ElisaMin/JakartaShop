package me.heizi.jsp.shopShit.dao.entities

import jakarta.persistence.*

@Entity(name = "productType")
@Table(name = "product_type")
open class ProductType {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0
    @Column
    var name:String? = null
}