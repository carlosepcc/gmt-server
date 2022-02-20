package com.server.gmt.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin
@RequestMapping("/login")
class SecurityController {

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var serviceUserDetails: UserDetailsService

    @Autowired
    private lateinit var serviceJwt: JWTService

    @PostMapping
    fun login(@RequestBody request: LoginRequest): String {
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(request.username, request.password))
        } catch (e: Exception) {
            throw Exception("Usuario o Contraseña Incorrecta", e)
        }
        val userDetails: UserDetails = serviceUserDetails.loadUserByUsername(request.username)
        return serviceJwt.generateToken(userDetails)
    }

}

class LoginRequest {
    val username: String = ""
    val password: String = ""
}