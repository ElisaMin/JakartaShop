package me.heizi.jsp.shopShit.utils

import jakarta.servlet.http.Cookie

fun findCookie(array: Array<Cookie>,name:String):Cookie? {
    for (cookie in array) if (cookie.name == name) return cookie
    return null
}