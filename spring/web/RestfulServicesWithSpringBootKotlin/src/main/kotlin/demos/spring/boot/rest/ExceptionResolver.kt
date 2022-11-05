package demos.spring.boot.rest

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@ControllerAdvice
@RestController
class ExceptionResolver {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [DeletionException::class])
    fun deleteError(ex: Exception): String {
        return String.format("[\"Root cause was - %s\"]", ex.message)
    }
}
