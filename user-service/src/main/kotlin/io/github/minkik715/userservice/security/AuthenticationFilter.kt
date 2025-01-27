package io.github.minkik715.userservice.security

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.minkik715.userservice.service.UserService
import io.github.minkik715.userservice.proterty.JwtProperties
import io.github.minkik715.userservice.vo.RequestLogin
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*

class AuthenticationFilter(
    private val objectMapper: ObjectMapper,
    private val authenticationManager: AuthenticationManager,
    private val userService: UserService,
    private val jwtProperties: JwtProperties
) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {

        val loginRequest = objectMapper.readValue(request.inputStream, RequestLogin::class.java)

        return authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginRequest.email,
                loginRequest.password,
                listOf()
            )
        )
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        val email = (authResult.principal as User).username

        val key = Keys.hmacShaKeyFor(jwtProperties.secret.toByteArray())
        userService.getUserDetailsByEmail(email).let { user ->
            val token = Jwts.builder()
                .subject(user.userId)
                .expiration(Date(System.currentTimeMillis() + jwtProperties.expire))
                .signWith(key, Jwts.SIG.HS256)
                .compact()
            response.addHeader("token", token)
            response.addHeader("userId", user.userId)
        }



    }
}