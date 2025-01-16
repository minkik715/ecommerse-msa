package io.github.minkik715.orderservice.vo

import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResponseOrder(
  val productId: String,
  val orderId: String,
  val userId: String,
  val qty: Int,
  val unitPrice: Int,
  val totalPrice: Int,
  val createdAt: Date
)