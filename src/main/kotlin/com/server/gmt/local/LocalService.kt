package com.server.gmt.local

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface LocalService {
    fun list(): Set<Local>
    fun save(local: Local): Local
}

@Service
class LocalServiceImplementation : LocalService {

    @Autowired
    lateinit var repository: LocalRepository

    override fun list(): Set<Local> {
        return repository.findAll().toSet()
    }

    override fun save(local: Local): Local {
        return repository.save(local)
    }

}