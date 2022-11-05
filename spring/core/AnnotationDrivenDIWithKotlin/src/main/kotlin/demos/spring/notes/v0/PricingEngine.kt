package demos.spring.notes.v0

class PricingEngine(private val minimumDiscountAmount: Double, private val percentageDiscount: Int) {
    fun price(itemNo: String?, quantity: Int): Double {
        var price = quantity * 5.50
        if (price >= minimumDiscountAmount) {
            price = price - price * (percentageDiscount / 100)
        }
        return price
    }

}
