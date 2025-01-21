package io.github.minkik715.apigatewayservice.config

import io.github.minkik715.apigatewayservice.properties.JwtProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PropertiesConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "jwt", ignoreUnknownFields = false)
    fun jwtProperties(): JwtProperties {
        return JwtProperties()
    }
}