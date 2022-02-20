package com.server.gmt.user

import javax.persistence.*
import com.server.gmt.helper.Entity as MyEntity

@Entity
@Table(name = "Usuario")
class User : MyEntity() {

    enum class Role { User }

    @Column
    var name: String = ""

    @Column
    var username: String = ""

    @Column
    var password: String = ""

    @Column
    @Enumerated(EnumType.STRING)
    var role: Role = Role.User
}