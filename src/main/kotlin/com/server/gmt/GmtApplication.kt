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
        if (userService.count() == 0) {
            val user: User = User()

            user.name = "Jesús F. Vázquez Biltre"
            user.username = "jesus"
            user.password = "1234"
            userService.new(user)

            user.name = "Arian León Benitez"
            user.username = "arian"
            user.password = "1234"
            userService.new(user)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<GmtApplication>(*args)
}
