package io.github.minkik715.userservice.controller

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user-service")
class UserController {

    val log = LoggerFactory.getLogger(UserController::class.java);
    @GetMapping("/welcome")
    fun welcome(@RequestHeader("user-request-header", required = false) header: String?): String {
        log.info(header)
        return "welcome"
    }

    @GetMapping("/check")
    fun welcome(): String {
        log.info("췤@ ${System.currentTimeMillis()}")
        return "Hi checker"
    }
}