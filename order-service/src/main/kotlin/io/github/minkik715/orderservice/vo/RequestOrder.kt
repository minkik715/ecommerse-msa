package io.github.minkik715.orderservice.vo

import java.util.*

data class RequestOrder(
    val productId: String,
    val qty: Int,
    val unitPrice: Int,
    val userId: String,
)