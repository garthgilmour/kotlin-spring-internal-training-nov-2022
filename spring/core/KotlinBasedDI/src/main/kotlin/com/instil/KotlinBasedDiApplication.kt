package com.instil

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.support.GenericApplicationContext

@SpringBootApplication
class KotlinBasedDiApplication {
    @Bean
    fun demo(context: GenericApplicationContext): CommandLineRunner {
        return CommandLineRunner {
            beans().initialize(context)

            val shop = context.getBean("shopWithMocks", Shop::class.java)

            println("----- Trying to make purchase -----")
            if (shop.makePurchase("ABC123", 9, "DEF456GHI78")) {
                println("\tSample purchase succeeded")
            } else {
                println("\tSample purchase failed")
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<KotlinBasedDiApplication>(*args)
}
