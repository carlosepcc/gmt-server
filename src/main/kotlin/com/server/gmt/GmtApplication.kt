package com.server.gmt

import com.server.gmt.user.User
import com.server.gmt.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GmtApplication : CommandLineRunner {

    @Autowired
    private lateinit var userService: UserService

    override fun run(vararg args: String?) {
        val list = mutableListOf<User>()

        val jesus = User()
        jesus.name = "Jesús F. Vázquez Biltre"
        jesus.username = "jesus"
        jesus.password = "1234"
        jesus.role = User.Role.User
        list.add(jesus)

        val arian = User()
        arian.name = "Arian León Benitez"
        arian.username = "arian"
        arian.password = "1234"
        arian.role = User.Role.User
        list.add(arian)

        list.forEach {
            if (!userService.exist(it)) {
                userService.new(it)
            }
        }

    }
}

fun main(args: Array<String>) {
    runApplication<GmtApplication>(*args)
}
