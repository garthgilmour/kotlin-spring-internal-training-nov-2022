package com.instil

interface PricingEngine {
	fun price(itemNo: String, quantity: Int): Double
}
