package io.github.minkik715.userservice.vo


class JwtProperties {
    var secret: String = "PRIVATE_MK_JWT_SECRET"
    var expire: Int = 3600000
}