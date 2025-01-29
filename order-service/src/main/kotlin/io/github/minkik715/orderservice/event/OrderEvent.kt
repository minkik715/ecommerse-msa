package io.github.minkik715.orderservice.event

import io.github.minkik715.orderservice.entity.OrderEntity

data class OrderEvent(
    val order: OrderEntity
)
