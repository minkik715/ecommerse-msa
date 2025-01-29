package io.github.minkik715.catalogservice.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

@EnableKafka
@Configuration
class KafkaConsumerConfig {

    companion object{
        const val ECOMMERCE_ORDER_QTY_TOPIC = "ecommerce.catalog-topic"
    }


    @Bean
    fun consumerFactory(): ConsumerFactory<String, String> {

        val properties = hashMapOf<String, Any>();

        properties[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "127.0.0.1:9094";
        properties[ConsumerConfig.GROUP_ID_CONFIG] = "consumerGroupId";
        properties[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java;
        properties[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java;

        return DefaultKafkaConsumerFactory(properties);
    }

    @Bean
    fun kafkaListenerContainerFactory(consumerFactory: ConsumerFactory<String, String>): ConcurrentKafkaListenerContainerFactory<String, String> {
        val kafkaListenerContainerFactory = ConcurrentKafkaListenerContainerFactory<String, String>()
        kafkaListenerContainerFactory.consumerFactory = consumerFactory
        return kafkaListenerContainerFactory
    }


}