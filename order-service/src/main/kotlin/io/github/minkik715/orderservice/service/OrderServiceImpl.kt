package io.github.minkik715.orderservice.service

import io.github.minkik715.orderservice.dto.OrderDto
import io.github.minkik715.orderservice.entity.OrderEntity
import io.github.minkik715.orderservice.repository.OrderRepository
import io.github.minkik715.orderservice.vo.RequestOrder
import io.github.minkik715.orderservice.vo.ResponseOrder
import org.springframework.stereotype.Service

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository
): OrderService {
    override fun createOrder(orderRequest: RequestOrder): ResponseOrder {

        return orderRepository.save(OrderEntity(OrderDto(orderRequest))).toResponse()
    }

    override fun getByOrderId(orderId: String): ResponseOrder {
        return orderRepository.findByOrderId(orderId)?.toResponse()
            ?: throw IllegalArgumentException("not found order: $orderId")
    }

    override fun getAllByUserId(userId: String): List<ResponseOrder> {
        return orderRepository.findAllByUserId(userId).map { it.toResponse() }
    }
}