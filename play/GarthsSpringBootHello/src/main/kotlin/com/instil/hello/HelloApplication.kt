package com.instil.hello

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class HelloApplication {
	@Bean
	fun betty() : CommandLineRunner {
		return CommandLineRunner { println("Betty component running...") }
	}

	@Bean
	fun barney() = CommandLineRunner { println("Barney component running...") }
}

fun main(args: Array<String>) {
	runApplication<HelloApplication>(*args)
}
