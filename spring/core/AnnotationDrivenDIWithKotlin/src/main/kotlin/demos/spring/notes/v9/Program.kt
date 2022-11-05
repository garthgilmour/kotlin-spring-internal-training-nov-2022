package demos.spring.notes.v9

import demos.spring.notes.common.Shop
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.support.AbstractApplicationContext

fun main() {
    buildContext().use { context ->
        val shop1 = context.getBean("shop", Shop::class.java)
        val shop2 = context.getBean("shop", Shop::class.java)
        useShop(shop1)
        useShop(shop2)
        testDependencies(shop1 as SampleShop, shop2 as SampleShop)
    }
}

fun testDependencies(shop1: SampleShop, shop2: SampleShop) {
    if (shop1 === shop2) {
        println("There is a single shop")
    } else {
        println("There are multiple shops")
    }
    if (shop1.pricingEngine === shop2.pricingEngine) {
        println("There is a single pricing engine")
    } else {
        println("There are multiple pricing engines")
    }
    if (shop1.paymentEngine === shop2.paymentEngine) {
        println("There is a single payment engine")
    } else {
        println("There are multiple payment engines")
    }
    if (shop1.stockCheckEngine === shop2.stockCheckEngine) {
        println("There is a single stock check engine")
    } else {
        println("There are multiple stock check engines")
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
    return AnnotationConfigApplicationContext().apply {
        register(SampleConfig::class.java)
        register(SampleShop::class.java)
        refresh()
    }
}
