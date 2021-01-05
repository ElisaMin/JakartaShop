package me.heizi.jsp.shopShit.dao.entities

import jakarta.persistence.*

@Entity(name = "product")
@Table(name = "products")
open class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id = 0

    @Column(nullable = false) open lateinit var name:String

    @Column open var price:Double = 0.0
    @Column open var quantity = 0
    @Column open var sold = 0

    @Column open var image:String? = null
    @Column open var info:String? = null
    @Column(name = "create_time") open var createTime:String? = null
    @Column(name = "isHit") open var hit =0

    @Column(name="type_id")
    open var typeId = 0

    @Transient
    var typeName:String?=null
}