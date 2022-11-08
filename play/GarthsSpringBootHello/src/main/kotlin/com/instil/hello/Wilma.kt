package com.instil.hello

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class Wilma : CommandLineRunner {
    @PostConstruct
    fun startup() {
        println("Wilma component created")
    }

    override fun run(vararg args: String?) {
        println("Wilma component running...")
    }
}
