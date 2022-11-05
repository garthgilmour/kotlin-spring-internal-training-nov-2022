package com.instil

class PricingEngineMock(private val minimumDiscountAmount: Int,
                        private val percentageDiscount: Int,
                        private val prices: List<Double>) : PricingEngine {
    private var priceCount: Int = -1

	override fun price(itemNo: String, quantity: Int): Double {
        println("\tCalculating price...")
        if(++priceCount == prices.size) {
			priceCount = 0
		}
        var price  = quantity * prices[priceCount]
        if(price >= minimumDiscountAmount) {
            price -= (price * (percentageDiscount / 100))
        }
        return price;
    }
}
