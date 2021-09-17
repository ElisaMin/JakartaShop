package me.heizi.jsp.shop.filter

import jakarta.inject.Inject
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerRequestFilter
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.Provider
import me.heizi.jsp.shop.R
import me.heizi.jsp.shop.filter.annotations.AdminOnly
import me.heizi.jsp.shop.services.AdminService
import me.heizi.jsp.shop.services.LoginService

@AdminOnly
@Provider
class AdminBlocker :ContainerRequestFilter {
    @Inject private lateinit var login:LoginService
    @Inject private lateinit var admin:AdminService

    override fun filter(requestContext: ContainerRequestContext) {
        login.getIDByKey(requestContext.cookies[R.cookie.key]?.value?.toInt())?.let { id ->
            admin.isAdmin(id)
        }.let { if (it!=true) Response
            .status(Response.Status.BAD_REQUEST)
            .entity("<html>${R.HTMLFragment.gotoHome}不是管理员!</html>")
            .type(MediaType.TEXT_HTML_TYPE)
            .build()
            .let(requestContext::abortWith)
        }
    }
}