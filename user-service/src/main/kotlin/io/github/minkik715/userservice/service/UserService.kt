package io.github.minkik715.userservice.service

import io.github.minkik715.userservice.dto.UserDto
import io.github.minkik715.userservice.vo.ResponseUser

interface UserService {
    fun createUser(userDto: UserDto): ResponseUser
    fun getUserByUserId(userId: String): ResponseUser
    fun getUsers(): List<ResponseUser>
}