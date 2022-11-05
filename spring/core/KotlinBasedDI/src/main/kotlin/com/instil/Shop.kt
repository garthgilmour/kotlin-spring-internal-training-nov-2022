package com.instil

class Shop(private val paymentEngine: PaymentEngine,
           private val pricingEngine: PricingEngine,
           private val stockCheckEngine: StockCheckEngine) {

	fun makePurchase(itemNo: String, quantity: Int, cardNo: String): Boolean {
		if(stockCheckEngine.check(itemNo) >= quantity) {
			val charge = pricingEngine.price(itemNo, quantity)
			if(paymentEngine.authorize(cardNo, charge)) {
				return true;
			}
		}
		return false;
	}
}
