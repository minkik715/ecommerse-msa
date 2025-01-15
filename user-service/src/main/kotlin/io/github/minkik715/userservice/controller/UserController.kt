package io.github.minkik715.userservice.controller

import io.github.minkik715.userservice.dto.UserDto
import io.github.minkik715.userservice.service.UserService
import io.github.minkik715.userservice.vo.GreetingProperties
import io.github.minkik715.userservice.vo.RequestUser
import io.github.minkik715.userservice.vo.ResponseUser
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user-service")
class UserController(
    private val userService: UserService,
    private val greetingProperties: GreetingProperties
) {
    val log = LoggerFactory.getLogger(UserController::class.java);


    @PostMapping("/users")
    fun createUser(@RequestBody user: RequestUser): ResponseEntity<ResponseUser> {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            userService.createUser(UserDto(user)
            )
        )
    }

    @GetMapping("/users")
    fun getUsers(): ResponseEntity<List<ResponseUser>> {
        return ResponseEntity.status(HttpStatus.OK).body(
            userService.getUsers()
        )
    }

    @GetMapping("/users/{userId}")
    fun getUser(@PathVariable userId: String): ResponseEntity<ResponseUser> {
        return ResponseEntity.status(HttpStatus.OK).body(
            userService.getUserByUserId(userId)
        )
    }
}