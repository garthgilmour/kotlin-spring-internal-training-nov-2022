package com.instil.di.groovy

import com.instil.di.groovy.beans.SampleShop
import com.instil.di.groovy.utils.Utils
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class GroovyApplication {

	@Bean
	CommandLineRunner startup(SampleShop shop) {
        var printTitle = {
            println("\n\n***** Groovy and Java Components working together in Spring Boot *****\n")
        }

        return {
            printTitle()
            Utils.demoShop(shop)
        }
    }

    static void main(String[] args) {
		SpringApplication.run(GroovyApplication, args)
	}
}
