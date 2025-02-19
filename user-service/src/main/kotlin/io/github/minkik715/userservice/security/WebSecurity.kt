package io.github.minkik715.userservice.security

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.minkik715.userservice.service.UserService
import io.github.minkik715.userservice.proterty.JwtProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurity(
    private val objectMapper: ObjectMapper,
    private val userService: UserService,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val jwtPropertiese: JwtProperties
){

    @Bean
    fun filterChain(http: HttpSecurity,): SecurityFilterChain {
        val authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder);

        val authenticationManager = authenticationManagerBuilder.build()


        http.csrf { it.disable() }
            .authenticationManager(authenticationManager)
            .addFilter(AuthenticationFilter(objectMapper, authenticationManager, userService,jwtPropertiese ))
            .authorizeHttpRequests {
            it
                .requestMatchers("/**")
                .permitAll()
                //.requestMatchers("/user-service/users/**")
                //.permitAll()

        }
        http.headers { it.frameOptions { it.disable() } }
        return http.build()
    }


}