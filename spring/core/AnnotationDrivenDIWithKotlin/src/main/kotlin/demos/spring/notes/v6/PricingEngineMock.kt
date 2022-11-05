package demos.spring.notes.v6

import demos.spring.notes.common.PricingEngine

class PricingEngineMock : PricingEngine {
    override fun price(itemNo: String, quantity: Int): Double {
        return quantity * 5.50
    }
}
