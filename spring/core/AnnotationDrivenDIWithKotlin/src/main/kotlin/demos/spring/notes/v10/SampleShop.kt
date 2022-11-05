package demos.spring.notes.v10

import demos.spring.notes.common.PaymentEngine
import demos.spring.notes.common.PricingEngine
import demos.spring.notes.common.Shop
import demos.spring.notes.common.StockCheckEngine
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service("shop")
class SampleShop @Autowired constructor(
        @Qualifier("pricing") private val pricingEngine: PricingEngine,
        @Qualifier("stock") private val stockCheckEngine: StockCheckEngine,
        @Qualifier("payment") private val paymentEngine: PaymentEngine) : Shop {

    var shopName: String = ""
        @Value("#{dataSource.CEO.toUpperCase() == 'STEVE JOBS' ? 'Apple' : 'Microsoft'}")
        set(value) {
            field = value
        }

    var openDuringWeekends = false
        @Value("#{dataSource.openDays matches '.*(Saturday|Sunday).*'}")
        set(value) {
            field = value
        }


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
