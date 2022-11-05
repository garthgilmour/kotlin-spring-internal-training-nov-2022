package demos.spring.notes.v3

import demos.spring.notes.common.Shop
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.support.AbstractApplicationContext

fun main() {
    buildContext().use { context ->
        val shop = context.getBean("shop", Shop::class.java)
        useShop(shop)
    }
}

fun useShop(shop: Shop) {
    if (shop.makePurchase("AB123", 20, "DEF456GHI78")) {
        println("Purchase Succeeded")
    } else {
        println("Purchase Failed")
    }
}

fun buildContext(): AbstractApplicationContext {
    val context = AnnotationConfigApplicationContext()
    with(context) {
        register(PaymentEngineMock::class.java)
        register(PricingEngineMock::class.java)
        register(StockCheckEngineMock::class.java)
        register(SampleShop::class.java)
        refresh()
    }
    return context
}
