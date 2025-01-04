package io.github.minkik715.apigatewayservice.filter

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class GlobalFilter : AbstractGatewayFilterFactory<GlobalFilter.Config>(Config::class.java){

    data class Config(
        var baseMessage: String,
        var preLogger: Boolean,
        var postLogger: Boolean
    )

    val log = LoggerFactory.getLogger(GlobalFilter::class.java);
    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val request = exchange.request
            val response = exchange.response

            log.info("Global Filter baseMessage : baseMessage -> ${config.baseMessage}")

            if(config.preLogger){
                log.info("Global Filter PRE filter : request id -> ${request.id}")
            }

            chain.filter(exchange).then(Mono.fromRunnable {
                if(config.postLogger){
                    log.info("Global Filter baseMessage POST filter : resposne code -> ${response.statusCode}")
                }
            })
        }
    }
}