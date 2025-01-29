package io.github.minkik715.orderservice.service

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.minkik715.orderservice.config.KafkaProducerConfig
import io.github.minkik715.orderservice.event.OrderEvent
import org.springframework.context.event.EventListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.event.TransactionalEventListener

@Service
class KafkaProducerService(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {

    @EventListener(OrderEvent::class)
    fun produceQty(orderEvent: OrderEvent){
        val message = objectMapper.writeValueAsString(orderEvent.order)
        kafkaTemplate.send(KafkaProducerConfig.ECOMMERCE_ORDER_QTY_TOPIC, message)
    }

}