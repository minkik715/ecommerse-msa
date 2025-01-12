package io.github.minkik715.userservice.vo

import java.util.*

data class ResponseUser(
    val email: String,
    val name: String,
    val userId: String,
    val createdAt: Date
)
