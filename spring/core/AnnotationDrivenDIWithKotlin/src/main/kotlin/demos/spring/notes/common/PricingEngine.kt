package demos.spring.notes.common

interface PricingEngine {
    fun price(itemNo: String, quantity: Int): Double
}
