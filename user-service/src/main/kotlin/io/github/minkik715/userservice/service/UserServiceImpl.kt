package io.github.minkik715.userservice.service

import io.github.minkik715.userservice.dto.UserDto
import io.github.minkik715.userservice.entity.UserEntity
import io.github.minkik715.userservice.repository.UserRepository
import io.github.minkik715.userservice.vo.ResponseUser
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
): UserService {
    override fun createUser(userDto: UserDto): ResponseUser {
        return userRepository.save(UserEntity(userDto)).toResponse()
    }
}