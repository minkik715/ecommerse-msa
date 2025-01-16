package io.github.minkik715.orderservice.controller

import io.github.minkik715.orderservice.dto.OrderDto
import io.github.minkik715.orderservice.service.OrderService
import io.github.minkik715.orderservice.vo.RequestOrder
import io.github.minkik715.orderservice.vo.ResponseOrder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order-service")
class OrderController(
    private val orderService: OrderService
) {

    @GetMapping("/{userId}/orders")
    fun getUserOrders(@PathVariable userId: String): ResponseEntity<List<ResponseOrder>> {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllByUserId(userId))
    }

    @GetMapping("/orders/{orderId}")
    fun getOrder(@PathVariable orderId: String): ResponseEntity<ResponseOrder> {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getByOrderId(orderId))
    }

    @PostMapping("/orders")
    fun createOrder(@RequestBody orderRequest: RequestOrder): ResponseEntity<ResponseOrder> {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderRequest))
    }
}