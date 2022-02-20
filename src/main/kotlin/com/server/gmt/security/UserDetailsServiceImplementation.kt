package com.server.gmt.security

import com.server.gmt.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImplementation : UserDetailsService {

    @Autowired
    private lateinit var userService: UserService

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = username?.let { userService.getByUsername(it) }
        val authorities: Set<SimpleGrantedAuthority> = mutableSetOf()
        authorities.plus(SimpleGrantedAuthority(user!!.role.name))
        return User(user.username, user.password, authorities)
    }
}