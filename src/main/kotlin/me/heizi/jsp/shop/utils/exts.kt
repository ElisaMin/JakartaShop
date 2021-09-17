package me.heizi.jsp.shop.utils

import jakarta.mvc.Models
import java.nio.charset.Charset

operator fun Models.set(key:String,value:Any?) =
    put(key,value)
fun String.convertISO88591toUTF8() = String(toByteArray(Charset.forName("ISO-8859-1")),Charsets.UTF_8)