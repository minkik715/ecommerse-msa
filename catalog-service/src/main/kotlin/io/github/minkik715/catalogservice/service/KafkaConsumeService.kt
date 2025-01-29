package io.github.minkik715.catalogservice.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.minkik715.catalogservice.config.KafkaConsumerConfig
import io.github.minkik715.catalogservice.repository.CatalogRepository
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class KafkaConsumeService(
    private val catalogRepository: CatalogRepository,
    private val objectMapper: ObjectMapper
) {

    val log = LoggerFactory.getLogger(KafkaConsumeService::class.java)


    @KafkaListener(topics = [KafkaConsumerConfig.ECOMMERCE_ORDER_QTY_TOPIC])
    @Transactional
    fun consumeQty(message: String) {
        log.debug("Kafka Message: $message")

        val readValue = objectMapper.readValue(message, object : TypeReference<Map<String, String>>() {})

        val productId = readValue["productId"] ?: throw IllegalArgumentException()

        catalogRepository.findByProductId(productId)?.let {
            it.stock -= Integer.valueOf(readValue["qty"])
        }
    }
}