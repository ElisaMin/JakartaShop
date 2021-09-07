package me.heizi.jsp.shopShit.utils

import jakarta.mvc.Models

operator fun Models.set(key:String,value:Any?) =
    put(key,value)