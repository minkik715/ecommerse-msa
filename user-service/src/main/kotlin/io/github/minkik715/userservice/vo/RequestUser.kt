package io.github.minkik715.userservice.vo

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class RequestUser(
    @NotNull(message = "Email cannot be null")
    @Size(min =2, message = "Email not be less than two characters")
    val email: String,

    @NotNull(message = "Name cannot be null")
    @Size(min =2, message = "Name not be less than two characters")
    val name: String,

    @NotNull(message = "Password cannot be null")
    @Size(min =8, message = "Password not be less than eight characters")
    val pwd: String
)