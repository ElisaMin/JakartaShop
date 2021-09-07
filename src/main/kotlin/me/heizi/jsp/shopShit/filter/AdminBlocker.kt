package me.heizi.jsp.shopShit.filter

import jakarta.inject.Inject
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerRequestFilter
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.Provider
import me.heizi.jsp.shopShit.AdminChecker
import me.heizi.jsp.shopShit.LoginChecker
import me.heizi.jsp.shopShit.R
import me.heizi.jsp.shopShit.filter.annotations.AdminOnly

@AdminOnly
@Provider
class AdminBlocker :ContainerRequestFilter {
    @Inject private lateinit var login:LoginChecker
    @Inject private lateinit var admin:AdminChecker

    override fun filter(requestContext: ContainerRequestContext) {
        login.getIDByKey(requestContext.cookies[R.cookie.key]?.value?.toInt())?.let { id ->
            admin.isAdmin(id)
        }.let { if (it!=true) Response
            .status(Response.Status.BAD_REQUEST)
            .entity("不是管理员!")
            .build()
            .let(requestContext::abortWith)
        }
    }
}