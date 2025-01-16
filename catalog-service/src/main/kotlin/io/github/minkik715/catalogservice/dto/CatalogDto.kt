package io.github.minkik715.catalogservice.dto

data class CatalogDto(
    val productId: String,
    val qty: Int,
    val unitPrice: Int,
    val totalPrice: Int,

    val orderId: String,
    val userId: String,
)
