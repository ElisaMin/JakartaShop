package me.heizi.jsp.shopShit

import jakarta.inject.Named

@Named("R")
object R {
    object cookie {
//        const val id = "sign_id"
        const val key = "sign_key"
    }
    object HTMLFragment{
        const val gotoHome = """<script>setTimeout(()=>{window.location.href="/"},3000)</script>"""
    }
}