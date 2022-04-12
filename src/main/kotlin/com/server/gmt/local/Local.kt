package com.server.gmt.local

import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*
import com.server.gmt.helper.Entity as MyEntity

@Entity
class Local : MyEntity() {
    @Column(name = "number", unique = true, nullable = false)
    var number: Int? = null

    @OneToMany(cascade = [CascadeType.REMOVE], orphanRemoval = true)
    @JoinColumn(name = "local_id")
    var locales: MutableSet<Local>? = null;

}

interface LocalRepository : JpaRepository<Local, Int>