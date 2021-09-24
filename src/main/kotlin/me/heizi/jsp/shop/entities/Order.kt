package me.heizi.jsp.shop.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "orders")
data class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @Column var comment:String? = null,
    @Column(name = "comment_generate_time") var commentTime:Date? =null,
    @Column(name = "generate_time")
    var time:Date?=null,
    @Column(name = "done_yet") var doneYet: Int = 0
) {
    var isDone:Boolean;get() = doneYet == 1 ;set(isDoneYet) { doneYet = if (isDoneYet) 1 else 0 }

    @OneToOne lateinit var user: User
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    lateinit var subOrders:MutableSet<SubOrder>

    val price get() = subOrders.let {
        var count = 0.0
        subOrders.forEach { count+=it.price }
        count
    }
}