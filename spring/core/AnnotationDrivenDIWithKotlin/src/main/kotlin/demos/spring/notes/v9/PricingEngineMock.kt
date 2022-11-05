package demos.spring.notes.v9

import demos.spring.notes.common.PricingEngine
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component("pricing")
@Scope("prototype")
class PricingEngineMock : PricingEngine {
    override fun price(itemNo: String, quantity: Int): Double {
        return quantity * 5.50
    }
}
