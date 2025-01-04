package io.github.minkik715.apigatewayservice.filter

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.core.Ordered
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class LoggingFilter : AbstractGatewayFilterFactory<LoggingFilter.Config>(Config::class.java){

    data class Config(
        var baseMessage: String,
        var preLogger: Boolean,
        var postLogger: Boolean
    )

    val log = LoggerFactory.getLogger(LoggingFilter::class.java);
    override fun apply(config: Config): GatewayFilter {
        val f : GatewayFilter = OrderedGatewayFilter( { exchange, chain ->
            val request = exchange.request
            val response = exchange.response

            log.info("Logging Filter baseMessage: ${config.baseMessage}")

            if(config.preLogger){
                log.info("Logging PRE Filter : request id -> ${request.id}")
            }

            chain.filter(exchange).then(Mono.fromRunnable {
                if(config.postLogger){
                    log.info("Logging POST Filter : resposne code -> ${response.statusCode}")
                }
            })
        }, Ordered.HIGHEST_PRECEDENCE)
        return f

        /*return GatewayFilter { exchange, chain ->
            val request = exchange.request
            val response = exchange.response

            log.info("Logging Filter baseMessage: ${config.baseMessage}")

            if(config.preLogger){
                log.info("Logging PRE Filter : request id -> ${request.id}")
            }

            chain.filter(exchange).then(Mono.fromRunnable {
                if(config.postLogger){
                    log.info("Logging POST Filter : resposne code -> ${response.statusCode}")
                }
            })
        }*/
    }
}