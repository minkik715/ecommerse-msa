package io.github.minkik715.userservice.dto

import io.github.minkik715.userservice.entity.UserEntity
import io.github.minkik715.userservice.vo.RequestUser
import java.util.*

data class UserDto(
    val email: String,
    val name: String,
    val pwd: String,
    val createdAt: Date,
    val encryptedPwd: String,
    val userId: String = UUID.randomUUID().toString(),
){
    constructor(requestUser: RequestUser, encryptedPwd: String): this(
        requestUser.email,
        requestUser.name,
        requestUser.pwd,
        Date(),
        encryptedPwd
    )
}
