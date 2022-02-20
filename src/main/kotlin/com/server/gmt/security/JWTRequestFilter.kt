package com.server.gmt.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class JWTRequestFilter : OncePerRequestFilter() {

    @Autowired
    private lateinit var serviceUserDetails: UserDetailsServiceImplementation

    @Autowired
    private lateinit var serviceJwt: JWTService

    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
    ) {
        val authorizationHeader: String? = request.getHeader("Authorization")
        var userName: String? = null
        var jwt: String? = null

        if (authorizationHeader != null) {
            jwt = authorizationHeader
            userName = serviceJwt.extractUserName(jwt)
        }

        if (userName != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = serviceUserDetails.loadUserByUsername(userName)
            if (serviceJwt.validateToken(jwt!!, userDetails)) {
                val authenticationToken = UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.authorities
                )
                authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authenticationToken
            }
        }
        filterChain.doFilter(request, response)
    }
}