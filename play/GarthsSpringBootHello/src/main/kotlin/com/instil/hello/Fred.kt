package com.instil.hello

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class Fred : CommandLineRunner {
    @PostConstruct
    fun startup() {
        println("Fred component created")
    }

    override fun run(vararg args: String?) {
        println("Fred component running...")
    }
}
