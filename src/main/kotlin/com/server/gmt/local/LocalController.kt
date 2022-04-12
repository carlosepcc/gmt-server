package com.server.gmt.local

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/local")
class LocalController {

    @Autowired
    lateinit var service: LocalService

    @GetMapping
    @ResponseBody
    fun list(): List<LocalResponse> {
        return service.list().map { LocalResponse(it) }
    }

    @PostMapping
    @ResponseBody
    fun save(@RequestBody local: LocalRequest): LocalResponse {
        return LocalResponse(service.save(local.toLocal()))
    }
}

//Request
class LocalRequest(val number: Int) {

    @JsonIgnore
    fun toLocal(): Local {
        val local = Local()
        local.number = number
        return local
    }
}

//Response
class LocalResponse(@JsonIgnore val local: Local) {
    val number: Int = local.number!!
}