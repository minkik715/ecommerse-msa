package io.github.minkik715.userservice.service

import io.github.minkik715.userservice.dto.UserDto
import io.github.minkik715.userservice.vo.ResponseUser
import org.springframework.security.core.userdetails.UserDetailsService

interface UserService : UserDetailsService{
    fun createUser(userDto: UserDto): ResponseUser
    fun getUserByUserId(userId: String): ResponseUser
    fun getUserDetailsByEmail(email: String): UserDto
    fun getUsers(): List<ResponseUser>
}