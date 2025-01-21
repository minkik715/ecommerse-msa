package io.github.minkik715.apigatewayservice.filter

import io.github.minkik715.apigatewayservice.properties.JwtProperties
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.apache.http.HttpHeaders
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import javax.crypto.SecretKey

@Component
class AuthorizationHeaderFilter(
    private val jwtProperties: JwtProperties
): AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config>(Config::class.java) {
    class Config()

    val log = LoggerFactory.getLogger(AuthorizationHeaderFilter::class.java);

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val request = exchange.request

            if(!request.headers.containsKey(HttpHeaders.AUTHORIZATION)){
                return@GatewayFilter onError(exchange, "no authorization header", HttpStatus.UNAUTHORIZED)
            }

            val authHeader =  request.headers.get(HttpHeaders.AUTHORIZATION)!!.first()

            val jwt = authHeader.replace("Bearer", "")
            if(!jwtValid(jwt)){
                return@GatewayFilter onError(exchange, "JWT token is not valid $jwt", HttpStatus.UNAUTHORIZED)
            }
            Jwts.parser()

            chain.filter(exchange)
        }

    }

    private fun jwtValid(jwt: String): Boolean {
        var result = true
        val secretKey: SecretKey = Keys.hmacShaKeyFor(jwtProperties.secret.toByteArray())
        val subject = runCatching {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jwt).payload.subject
        }.onFailure {
            log.error(it.message)
            result = false
        }.getOrNull()

        if(subject.isNullOrEmpty()){
            result = false
        }
        return result
    }

    private fun onError(exchange: ServerWebExchange, err: String, httpStatus: HttpStatus): Mono<Void> {
        val response = exchange.response
        response.statusCode = httpStatus
        log.error(err)
        return response.setComplete()
    }

}