package com.instil

import com.instil.Result.*
import org.koin.core.Koin
import org.koin.core.qualifier.named
import org.koin.dsl.koinApplication
import org.koin.dsl.module

enum class Result { SUCCESS, OUT_OF_STOCK, AUTH_FAILED }

interface PricingEngine {
    fun price(quantity: Int, stockNum: String): Double
}

interface StockCheckEngine {
    fun checkQuantity(stockNum: String): Int
}

interface PaymentEngine {
    fun authorise(amount: Double, cardNum: String): Boolean
}

class Shop(private val pricingEngine: PricingEngine,
           private val stockEngine: StockCheckEngine,
           private val paymentEngine: PaymentEngine) {

    fun makePurchase(stockNum: String,
                     quantity: Int,
                     cardNum: String): Result {
        return if(stockEngine.checkQuantity(stockNum) >= quantity) {
            val charge = pricingEngine.price(quantity, stockNum)
            if(paymentEngine.authorise(charge, cardNum)) {
                SUCCESS
            } else {
                AUTH_FAILED
            }
        } else {
            OUT_OF_STOCK
        }
    }
}

class PricingEngineStub : PricingEngine {
    override fun price(quantity: Int, stockNum: String) = quantity * 1.2
}

class StockCheckEngineStub : StockCheckEngine {
    override fun checkQuantity(stockNum: String) = if(stockNum.startsWith("AB")) 100 else 10
}

class PaymentEngineStub : PaymentEngine {
    override fun authorise(amount: Double, cardNum: String) = amount < 100
}

fun main() {
    val koinInstance = koinApplication {
        printLogger()
        modules(
            module {
                //Configure and unnamed shop with unnamed dependencies
                // all components are singletons
                single { Shop(get(), get(), get()) }
                single<PricingEngine> { PricingEngineStub() }
                single<StockCheckEngine> { StockCheckEngineStub() }
                single<PaymentEngine> { PaymentEngineStub() }

                //Configure a named shop with unnamed dependencies
                // via a factory function
                factory(named("shop2")) { Shop(get(), get(), get()) }

                //Configure a named shop with named dependencies
                // via a factory function
                factory(named("shop3")) {
                    Shop(get(named("pricing")),
                         get(named("stock")),
                         get(named("payment")))
                }

                single(named("pricing")) { PricingEngineStub() }
                single(named("stock")) { StockCheckEngineStub() }
                single(named("payment")) { PaymentEngineStub() }
            }
        )
    }

    val koin = koinInstance.koin

    showInjectionByType(koin)

    showInjectionByName(koin)

    showFindingByType(koin)

    showDynamicConfiguration(koin)

}

private fun showInjectionByType(koin: Koin) {
    printTitle("Show DI By Type")
    val shop = koin.get<Shop>()
    val result = shop.makePurchase("AB12", 20, "DUMMY987")
    printTabbed(result)
}

private fun showInjectionByName(koin: Koin) {
    printTitle("Show DI By Name")

    val firstShop = koin.get<Shop>(named("shop2"))
    val secondShop = koin.get<Shop>(named("shop3"))

    val firstResult = firstShop.makePurchase("CD34", 20, "DUMMY987")
    printTabbed(firstResult)

    val secondResult = secondShop.makePurchase("CD34", 20, "DUMMY987")
    printTabbed(secondResult)
}

private fun showFindingByType(koin: Koin) {
    printTitle("Show Finding All")
    koin.getAll<Shop>().forEach {
        val result = it.makePurchase("AB12", 90, "DUMMY987")
        printTabbed(result)
    }
}

private fun showDynamicConfiguration(koin: Koin) {
    val tmp = Shop(PricingEngineStub(), StockCheckEngineStub(), PaymentEngineStub())
    koin.declare(tmp, named("shop4"))

    printTitle("Show Dynamic Configuration")
    val shop = koin.get<Shop>(named("shop4"))
    val result = shop.makePurchase("AB12", 20, "DUMMY987")
    printTabbed(result)
}

fun printTitle(title: String) = println("--- $title ---")
fun printTabbed(input: Any)  = println("\t$input")
