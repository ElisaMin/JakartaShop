package me.heizi.jsp.shop.utils

import java.text.SimpleDateFormat
import java.util.*

//获取现在时间
fun getFormattedTimeForNow() = Date().format()
val nowFormatted get()  = Date().format()

fun main() {
    println(getFormattedTimeForNow())
}
//YYYY-MM-DD HH:MM:SS.SSS
private val timeFormatter by lazy {
    SimpleDateFormat("YYYY-MM-dd HH:mm:ss:SSS", Locale.CHINA)
}
//date to string
fun Date.format(): String =
    timeFormatter.format(this)
