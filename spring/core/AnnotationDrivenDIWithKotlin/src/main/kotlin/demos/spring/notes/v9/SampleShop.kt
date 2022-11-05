package demos.spring.notes.v9

import demos.spring.notes.common.PaymentEngine
import demos.spring.notes.common.PricingEngine
import demos.spring.notes.common.Shop
import demos.spring.notes.common.StockCheckEngine
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service

@Service("shop")
@Scope("prototype")
class SampleShop @Autowired constructor(
        @Qualifier("pricing") val pricingEngine: PricingEngine,
        @Qualifier("stock") val stockCheckEngine: StockCheckEngine,
        @Qualifier("payment") val paymentEngine: PaymentEngine): Shop {

    override fun makePurchase(itemNo: String, quantity: Int, cardNo: String): Boolean {
        if (stockCheckEngine.check(itemNo) >= quantity) {
            val charge = pricingEngine.price(itemNo, quantity)
            if (paymentEngine.authorize(cardNo, charge)) {
                return true
            }
        }
        return false
    }
}
