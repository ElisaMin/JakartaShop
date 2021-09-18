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
    override fun toString(): String {
        return "Product(id=$id, price=$price, quantity=$quantity, sold=$sold, image=$image, info=$info, city=$city, createTime=$createTime, hit=$hit, using=$using, name='$name', type=$type)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Product) return false

        if (id != other.id) return false
        if (price != other.price) return false
        if (quantity != other.quantity) return false
        if (sold != other.sold) return false
        if (image != other.image) return false
        if (info != other.info) return false
        if (city != other.city) return false
        if (createTime != other.createTime) return false
        if (hit != other.hit) return false
        if (using != other.using) return false
        if (name != other.name) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + price.hashCode()
        result = 31 * result + quantity
        result = 31 * result + sold
        result = 31 * result + (image?.hashCode() ?: 0)
        result = 31 * result + (info?.hashCode() ?: 0)
        result = 31 * result + (city?.hashCode() ?: 0)
        result = 31 * result + (createTime?.hashCode() ?: 0)
        result = 31 * result + hit
        result = 31 * result + using
        result = 31 * result + name.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }


}