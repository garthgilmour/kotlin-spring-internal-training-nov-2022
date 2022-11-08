package com.instil.hello

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.support.AbstractApplicationContext
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class Dino {
    @Autowired
    private lateinit var context: AbstractApplicationContext

    @PostConstruct
    fun dumpBeans() {
        val beanNames = context.beanDefinitionNames
        println("The names of all the beans are:")
        for(name in beanNames) {
            println("\t $name")
        }
    }
}
