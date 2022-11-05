package demos.spring.notes.v4

import demos.spring.notes.common.PricingEngine
import org.springframework.stereotype.Component

@Component
class PricingEngineMock : PricingEngine {
    override fun price(itemNo: String, quantity: Int): Double {
        return quantity * 5.50
    }
}
