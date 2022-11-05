package demos.spring.notes.v0

class SampleShop {
    private val pricingEngine = PricingEngine(500.0, 10)
    private val stockCheckEngine = StockCheckEngine()
    private val paymentEngine = PaymentEngine("www.somewhere.com")

    fun makePurchase(itemNo: String?, quantity: Int, cardNo: String?): Boolean {
        if (stockCheckEngine.check(itemNo) >= quantity) {
            val charge: Double = pricingEngine.price(itemNo, quantity)
            if (paymentEngine.authorize(cardNo, charge)) {
                return true
            }
        }
        return false
    }
}
