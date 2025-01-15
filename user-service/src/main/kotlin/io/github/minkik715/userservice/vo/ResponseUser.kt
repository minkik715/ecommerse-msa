package io.github.minkik715.userservice.vo

import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResponseUser(
    val email: String,
    val name: String,
    val userId: String,
    val createdAt: Date,

    val orders: Collection<ResponseOrder> = mutableListOf()
)
