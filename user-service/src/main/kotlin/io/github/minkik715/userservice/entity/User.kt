package io.github.minkik715.userservice.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "users")
class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? =null
}