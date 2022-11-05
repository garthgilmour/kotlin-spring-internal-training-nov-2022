package demos.spring.notes.v5

import demos.spring.notes.common.PricingEngine

class PricingEngineMock : PricingEngine {
    override fun price(itemNo: String, quantity: Int): Double {
        return quantity * 5.50
    }
}
