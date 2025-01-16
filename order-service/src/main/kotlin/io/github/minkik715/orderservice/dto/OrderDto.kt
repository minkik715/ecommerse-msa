package io.github.minkik715.orderservice.dto

import io.github.minkik715.orderservice.vo.RequestOrder
import io.github.minkik715.orderservice.vo.ResponseOrder
import java.util.UUID

data class OrderDto(
    val qty: Int,
    val unitPrice: Int,

    val orderId: String,
    val userId: String,
    val totalPrice: Int,

    var productId: String
) {
    constructor(orderRequest: RequestOrder) : this(
        orderRequest.qty,
        orderRequest.unitPrice,
        UUID.randomUUID().toString(),
        orderRequest.userId,
        orderRequest.unitPrice * orderRequest.qty,
        orderRequest.productId
    )
}