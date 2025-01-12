package io.github.minkik715.userservice.repository

import io.github.minkik715.userservice.entity.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<UserEntity, Long>