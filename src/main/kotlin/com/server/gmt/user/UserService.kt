package com.server.gmt.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

interface UserService {
    fun new(user: User): User
    fun getByUsername(username: String): User
    fun count(): Int
}

@Service
class UserServiceImplementation : UserService {

    @Autowired
    private lateinit var repository: UserRepository

    @Autowired
    @Lazy
    private lateinit var passwordEncoder: PasswordEncoder

    override fun new(user: User): User {
        user.password = passwordEncoder.encode(user.password)
        return repository.save(user)
    }

    override fun getByUsername(username: String): User {
        return repository.findByUsername(username)
    }

    override fun count(): Int {
        return repository.count().toInt()
    }

}