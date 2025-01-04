package io.github.minkik715.apigatewayservice.filter

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CustomFilter : AbstractGatewayFilterFactory<CustomFilter.Config>(Config::class.java){
     class Config()

    val log = LoggerFactory.getLogger(CustomFilter::class.java);
    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val request = exchange.request
            val response = exchange.response

            log.info("Custrom PRE filter : request id -> ${request.id}")

            chain.filter(exchange).then(Mono.fromRunnable {
                log.info("Custom POST filter: response code -> ${response.statusCode}")
            })
        }
    }
}