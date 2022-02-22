package com.server.gmt.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.*
import com.server.gmt.helper.Entity as MyEntity

@Entity
@Table(name = "Usuario")
class User : MyEntity() {

    enum class Role { User }

    @Column(nullable = false)
    var name: String = ""

    @Column(nullable = false, unique = true)
    var username: String = ""

    @Column(nullable = false)
    var password: String = ""

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var role: Role = Role.User
}


@Repository
interface UserRepository : JpaRepository<User, Int> {
    fun findByUsername(username: String): User
}