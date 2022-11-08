package com.instil.staff

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.AbstractApplicationContext
import javax.annotation.PostConstruct

@SpringBootApplication
class StaffApplication(val context: AbstractApplicationContext) {
	@PostConstruct
	fun showBeans() {
		println("Registered beans:")
		context.beanDefinitionNames.forEach { name ->
			println("Bean named '$name'")
		}
	}

}

fun main(args: Array<String>) {
	runApplication<StaffApplication>(*args)
}
