package demos.spring.boot.rest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootApplication

fun main(args: Array<String>) {
	runApplication<demos.spring.boot.rest.SpringBootApplication>(*args)
}
