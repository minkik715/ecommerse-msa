package io.github.minkik715.userservice.feign

import io.github.minkik715.userservice.vo.ResponseOrder
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name="order-service")
interface OrderServiceClient {
    @GetMapping("/order-service/{userId}/orders_")
    fun getUserOrders(@PathVariable userId: String): List<ResponseOrder>
}