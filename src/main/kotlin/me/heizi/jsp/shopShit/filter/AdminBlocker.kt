package me.heizi.jsp.shopShit.filter

import jakarta.inject.Inject
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerRequestFilter
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.Provider
import me.heizi.jsp.shopShit.ApplicationScopeParameterSaver
import me.heizi.jsp.shopShit.R
import me.heizi.jsp.shopShit.filter.annotations.AdminOnly

@AdminOnly
@Provider
class AdminBlocker :ContainerRequestFilter {
    @Inject private lateinit var asps:ApplicationScopeParameterSaver
    override fun filter(requestContext: ContainerRequestContext) {
        (requestContext.cookies?.get(R.cookie.id)?.value?.let {
            kotlin.runCatching {
                asps.isAdmin(it.toInt())
            }.getOrNull()
        }).let {
            if (it!=true) {
                requestContext.abortWith(
                    Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity("不是管理员!")
                        .build()
                )
            }
        }
    }
}