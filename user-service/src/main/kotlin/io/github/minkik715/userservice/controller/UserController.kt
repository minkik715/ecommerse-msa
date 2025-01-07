package io.github.minkik715.userservice.controller

import io.github.minkik715.userservice.vo.GreetingProperties
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user-service")
class UserController(
    //private val env: Environment
    private val greetingProperties: GreetingProperties
) {
    val log = LoggerFactory.getLogger(UserController::class.java);



    @GetMapping("/welcome")
    fun welcome(): String {
        return greetingProperties.message
    }
}