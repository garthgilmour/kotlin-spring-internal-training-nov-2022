package com.instil

interface PaymentEngine {
	fun authorize(cardNo: String, amount: Double): Boolean
}
