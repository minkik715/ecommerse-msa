package io.github.minkik715.orderservice.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@EnableKafka
@Configuration
class KafkaProducerConfig {

    companion object{
        const val ECOMMERCE_ORDER_QTY_TOPIC = "ecommerce.catalog-topic"
    }
    @Bean
    fun producerFactory(): ProducerFactory<String, String> {

        val properties = hashMapOf<String, Any>();

        properties[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "127.0.0.1:9094";
        properties[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java;
        properties[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java;

        return DefaultKafkaProducerFactory(properties);
    }

    @Bean
    fun kafkaTemplate(producerFactory: ProducerFactory<String, String>) : KafkaTemplate<String, String>{
        return KafkaTemplate(producerFactory)
    }

}