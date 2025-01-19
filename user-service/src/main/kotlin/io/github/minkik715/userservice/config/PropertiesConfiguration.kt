package io.github.minkik715.userservice.config

import io.github.minkik715.userservice.vo.GreetingProperties
import io.github.minkik715.userservice.vo.JwtProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PropertiesConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "greeting", ignoreUnknownFields = false)
    fun greetingProperties(): GreetingProperties{
        return GreetingProperties()
    }

    @Bean
    @ConfigurationProperties(prefix = "jwt", ignoreUnknownFields = false)
    fun jwtProperties(): JwtProperties{
        return JwtProperties()
    }
}