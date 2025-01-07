package io.github.minkik715.userservice.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {

    @GetMapping("/health_check")
    fun status(): String {
        return "It's Working in User Service"
    }

}