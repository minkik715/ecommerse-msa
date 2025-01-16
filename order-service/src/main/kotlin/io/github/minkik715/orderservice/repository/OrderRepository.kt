package io.github.minkik715.orderservice.repository

import io.github.minkik715.orderservice.entity.OrderEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository: CrudRepository<OrderEntity, Long> {
    fun findByOrderId(orderId: String): OrderEntity?

    fun findAllByUserId(userId: String): List<OrderEntity>
}