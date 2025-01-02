package io.github.minkik715.apigatewayservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiGatewayServiceApplication

fun main(args: Array<String>) {
    runApplication<ApiGatewayServiceApplication>(*args)
}
