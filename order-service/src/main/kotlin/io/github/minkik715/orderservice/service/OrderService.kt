package io.github.minkik715.orderservice.service

import io.github.minkik715.orderservice.vo.RequestOrder
import io.github.minkik715.orderservice.vo.ResponseOrder

interface OrderService {
    fun createOrder(orderRequest: RequestOrder): ResponseOrder

    fun getByOrderId(orderId: String): ResponseOrder
    fun getAllByUserId(userId: String): List<ResponseOrder>
}