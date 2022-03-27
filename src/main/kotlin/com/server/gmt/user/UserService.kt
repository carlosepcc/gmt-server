package com.server.gmt.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher.matching
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

interface UserService {
    fun new(user: User): User
    fun getByUsername(username: String): User
    fun exist(user: User): Boolean
}

@Service
class UserServiceImplementation : UserService {

    @Autowired
    private lateinit var repository: UserRepository

    @Autowired
    @Lazy
    private lateinit var passwordEncoder: PasswordEncoder

    override fun new(user: User): User {
        if (user.password != null) user.password = passwordEncoder.encode(user.password)
        return repository.save(user)
    }

    override fun getByUsername(username: String): User {
        val user = User()
        user.username = username
        return repository.findOne(Example.of(user)).get()
    }

    override fun exist(user: User): Boolean {
        return repository.exists(Example.of(user, matching().withIgnorePaths("id", "password")))
    }

}