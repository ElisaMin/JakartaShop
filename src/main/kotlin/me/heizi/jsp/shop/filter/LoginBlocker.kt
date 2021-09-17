package me.heizi.jsp.shop.filter

import jakarta.inject.Inject
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerRequestFilter
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.Provider
import me.heizi.jsp.shop.R
import me.heizi.jsp.shop.filter.annotations.NotLoginOnly
import me.heizi.jsp.shop.services.LoginService

@NotLoginOnly
@Provider
class LoginBlocker:ContainerRequestFilter {

    @Inject private lateinit var login: LoginService

    override fun filter(requestContext: ContainerRequestContext) {
        if (login.getIDByKey(requestContext.cookies[R.cookie.key]?.value?.toInt())!=null)
            Response
                .status(403)
                .entity("<html>${R.HTMLFragment.gotoHome}已经登入！！！</html>")
                .type(MediaType.TEXT_HTML_TYPE)
                .build()
                .let(requestContext::abortWith)

    }
}