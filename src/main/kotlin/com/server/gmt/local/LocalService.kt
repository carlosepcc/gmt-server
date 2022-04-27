package com.server.gmt.local

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface LocalService {
    fun list(): Set<Local>
    fun save(local: Local): Local
    fun delete(ids: Array<Int>): List<Int>
    fun update(local: Local): Local
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

    override fun delete(ids: Array<Int>): List<Int> {
        repository.deleteAllById(ids.toList());
        return ids.toList();
    }

    override fun update(local: Local): Local {
        val pivote = local.id?.let { repository.findById(it).get() }
        pivote?.number = local.number;
        return repository.save(pivote!!)
    }

}