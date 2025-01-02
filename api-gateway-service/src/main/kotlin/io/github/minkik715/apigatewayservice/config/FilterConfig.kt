package io.github.minkik715.apigatewayservice.config

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

//@Configuration
class FilterConfig {
    //@Bean
    fun gatewayRoutes(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()
            .route {
                it.path("/first-service/**")
                    .filters {
                        it.addRequestHeader("first-request", "first-request-header")
                            .addResponseHeader("first-response", "first-response-header")
                    }
                    .uri("http://localhost:8081")
            }
            .route {
                it.path("/second-service/**")
                    .filters {
                        it.addRequestHeader("second-request", "second-request-header")
                            .addResponseHeader("second-response", "second-response-header")
                    }
                    .uri("http://localhost:8082")
            }
            .build()
    }
}