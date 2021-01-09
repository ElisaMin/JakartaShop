package me.heizi.jsp.shopShit.utils

import java.text.SimpleDateFormat
import java.util.*

//获取现在时间
fun getFormattedTimeForNow() = Date().format()


//YYYY-MM-DD HH:MM:SS.SSS
private val timeFormatter by lazy {
    SimpleDateFormat("YYYY-MM-DD HH:MM:SS.SSS", Locale.CHINA)
}
//date to string
fun Date.format(): String =
    timeFormatter.format(this)
