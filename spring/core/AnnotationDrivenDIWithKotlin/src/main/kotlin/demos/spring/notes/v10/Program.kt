package demos.spring.notes.v10

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.support.AbstractApplicationContext

fun main() {
    buildContext().use { context ->
        val shop = context.getBean("shop", SampleShop::class.java)
        println("----- Using shop '${shop.shopName}' -----")

        if (shop.openDuringWeekends) {
            println("\tShop open during weekends")
        } else {
            println("\tShop closed at weekends")
        }

        println("----- Trying to make purchase -----")
        if (shop.makePurchase("ABC123", 9, "DEF456GHI78")) {
            println("\tSample purchase succeeded")
        } else {
            println("\tSample purchase failed")
        }
    }
}

fun buildContext(): AbstractApplicationContext {
    return AnnotationConfigApplicationContext().apply {
        register(SampleConfig::class.java)
        register(SampleShop::class.java)
        refresh()
    }
}
