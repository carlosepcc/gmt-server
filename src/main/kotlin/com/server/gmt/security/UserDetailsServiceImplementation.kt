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
        val user = userService.getByUsername(username!!)
        val authorities: Set<SimpleGrantedAuthority> = setOf(SimpleGrantedAuthority(user.role.name))
        return User(user.username, user.password, authorities)
    }
}