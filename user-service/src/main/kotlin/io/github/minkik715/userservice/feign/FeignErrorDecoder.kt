package io.github.minkik715.userservice.feign

import feign.Response
import feign.codec.ErrorDecoder
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import java.lang.Exception

@Component
class FeignErrorDecoder : ErrorDecoder {
    override fun decode(methodKey: String, response: Response): Exception {
        val ex = when(response.status()){
            400 -> {

            }
            404 -> {

            }
            else -> {
            }
        }
        return Exception()
    }
}