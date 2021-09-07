package me.heizi.jsp.shopShit.filter

import jakarta.inject.Inject
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerRequestFilter
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.Provider
import me.heizi.jsp.shopShit.LoginChecker
import me.heizi.jsp.shopShit.R
import me.heizi.jsp.shopShit.filter.annotations.NotLoginOnly

@NotLoginOnly
@Provider
class LoginBlocker:ContainerRequestFilter {

    @Inject private lateinit var loginChecker: LoginChecker

    override fun filter(requestContext: ContainerRequestContext) {
        if (loginChecker.getIDByKey(requestContext.cookies[R.cookie.key]?.value?.toInt())!=null)
            Response
                .status(403)
                .entity("${R.HTMLFragment.gotoHome}已经登入！！！")
                .type(MediaType.TEXT_HTML_TYPE)
                .build()
                .let(requestContext::abortWith)

    }
}