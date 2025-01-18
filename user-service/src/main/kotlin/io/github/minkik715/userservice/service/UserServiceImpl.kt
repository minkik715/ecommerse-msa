package io.github.minkik715.userservice.service

import io.github.minkik715.userservice.dto.UserDto
import io.github.minkik715.userservice.entity.UserEntity
import io.github.minkik715.userservice.repository.UserRepository
import io.github.minkik715.userservice.vo.ResponseOrder
import io.github.minkik715.userservice.vo.ResponseUser
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository, private val passwordEncoder: BCryptPasswordEncoder
) : UserService {

    override fun loadUserByUsername(email: String): UserDetails {
        return userRepository.findByEmail(email)?.let {
            User(
                it.email, it.encryptedPwd, true, true, true, true, listOf()
            )
        } ?: throw UsernameNotFoundException("$email not found")
    }

    override fun createUser(userDto: UserDto): ResponseUser {
        val encodedPwd = passwordEncoder.encode(userDto.pwd)
        userDto.encryptedPwd = encodedPwd

        return userRepository.save(UserEntity(userDto)).toResponse()
    }

    override fun getUserByUserId(userId: String): ResponseUser {
        val orders: List<ResponseOrder> = mutableListOf()
        return userRepository.findByUserId(userId)?.toResponse(orders)
            ?: throw IllegalArgumentException("user not found $userId")
    }

    override fun getUserDetailsByEmail(email: String): UserDto {
        return userRepository.findByEmail(email)?.toDto()
            ?: throw IllegalArgumentException("$email not found")
    }

    override fun getUsers(): List<ResponseUser> {
        return userRepository.findAll().map {
            val orders: List<ResponseOrder> = mutableListOf()
            it.toResponse(orders)
        };
    }


}