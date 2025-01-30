package io.github.minkik715.userservice.config

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.timelimiter.TimeLimiterConfig
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder
import org.springframework.cloud.client.circuitbreaker.Customizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class Resilience4JConfig {
    @Bean
    fun globalCustomCircuitBreakerFactory(): Customizer<Resilience4JCircuitBreakerFactory> {
        val circuitBreakerConfig = CircuitBreakerConfig.custom()
            .failureRateThreshold(20f)
            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
            .slidingWindowSize(10) //최근 10중에 실패가 20% 이상이면 CircuitBreak Open
            .waitDurationInOpenState(Duration.ofMillis(1500)) // CircuitBreak Open 유지시간 1.5초
            .build()

        val timeLimiterConfig = TimeLimiterConfig.custom() //최대 4초 그 이상은 실패로 간주함
            .timeoutDuration(Duration.ofMillis(4000))
            .build()

        return Customizer { factory ->
            factory.configureDefault { id ->
                Resilience4JConfigBuilder(id)
                    .timeLimiterConfig(timeLimiterConfig)
                    .circuitBreakerConfig(circuitBreakerConfig)
                    .build()
            }
        }

    }
}