package com.instil

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import reactor.core.publisher.Mono

@SpringBootApplication
class HelloSpringBootKotlinApplication {
	@Bean(name = ["helloMsg"])
	fun buildMsg() = Mono.just("Hello Spring Boot!")

	@Bean
	fun startup(@Qualifier("helloMsg") message: Mono<String?>) =
		CommandLineRunner {
			message.subscribe(
				{ x: String? -> println(x) },
				{ error: Throwable -> System.err.println("Whoops: " + error.message) },
				{ println("...bye for now.") }
			)
		}
}

fun main(args: Array<String>) {
	runApplication<HelloSpringBootKotlinApplication>(*args)
}
