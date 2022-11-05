package demos.spring.notes.v8

import demos.spring.notes.common.PaymentEngine
import demos.spring.notes.common.PricingEngine
import demos.spring.notes.common.Shop
import demos.spring.notes.common.StockCheckEngine
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Service("shop")
class SampleShop @Autowired constructor(
        @Qualifier("pricing") private val pricingEngine: PricingEngine,
        @Qualifier("stock") private val stockCheckEngine: StockCheckEngine,
        @Qualifier("payment") private val paymentEngine: PaymentEngine): Shop {

    override fun makePurchase(itemNo: String, quantity: Int, cardNo: String): Boolean {
        if (stockCheckEngine.check(itemNo) >= quantity) {
            val charge = pricingEngine.price(itemNo, quantity)
            if (paymentEngine.authorize(cardNo, charge)) {
                return true
            }
        }
        return false
    }

    @PostConstruct
    fun begin() {
        println("Spring context just created a shop")
    }

    @PreDestroy
    fun end() {
        println("Spring context just released a shop")
    }
}
