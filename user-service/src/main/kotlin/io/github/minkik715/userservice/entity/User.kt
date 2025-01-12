package io.github.minkik715.userservice.entity

import io.github.minkik715.userservice.dto.UserDto
import io.github.minkik715.userservice.vo.ResponseUser
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.Date

@Entity(name = "users")
class UserEntity(
    userDto: UserDto
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false, unique = true)
    val userId: String = userDto.userId

    @Column(nullable = false, length = 50, unique = true)
    var email: String = userDto.email

    @Column(nullable = false, length = 50)
    var name: String = userDto.name

    @Column(nullable = false, length = 50)
    var encryptedPwd: String = userDto.encryptedPwd

    @Column
    var createdAt: Date = userDto.createdAt

    fun toResponse(): ResponseUser {
        return ResponseUser(
            this.email,
            this.name,
            this.userId,
            this.createdAt,
        )
    }
}