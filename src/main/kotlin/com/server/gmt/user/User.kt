package com.server.gmt.user

import org.springframework.data.geo.GeoResult
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.*
import com.server.gmt.helper.Entity as MyEntity

@Entity
@Table(name = "Usuario")
class User : MyEntity() {

    enum class Role { User }

    @Column(nullable = false)
    var name: String? = null

    @Column(nullable = false, unique = true)
    var username: String? = null

    @Column(nullable = false)
    var password: String? = null

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var role: Role? = null
}


@Repository
interface UserRepository : JpaRepository<User, Int>