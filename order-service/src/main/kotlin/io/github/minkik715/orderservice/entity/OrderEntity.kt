package io.github.minkik715.orderservice.entity

import io.github.minkik715.orderservice.dto.OrderDto
import io.github.minkik715.orderservice.vo.ResponseOrder
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import java.util.*

@Entity(name = "orders")
class OrderEntity(orderDto: OrderDto) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false, unique = true)
    val orderId: String = orderDto.orderId

    @Column(nullable = false)
    val userId: String = orderDto.userId

    @Column(nullable = false)
    val productId: String = orderDto.productId

    @Column(nullable = false)
    var qty: Int = orderDto.qty

    @Column(nullable = false)
    var unitPrice: Int = orderDto.unitPrice

    @Column(nullable = false)
    var totalPrice: Int = orderDto.totalPrice


    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    var createdAt: Date = Date()

    fun toResponse(orders: Collection<ResponseOrder> = mutableListOf()): ResponseOrder {
        return ResponseOrder(
            this.productId,
            this.orderId,
            this.userId,
            this.qty,
            this.unitPrice,
            this.totalPrice,
            this.createdAt
        )
    }
}