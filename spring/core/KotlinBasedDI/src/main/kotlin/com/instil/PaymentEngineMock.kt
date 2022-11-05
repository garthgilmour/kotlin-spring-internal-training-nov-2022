package com.instil

class PaymentEngineMock(private val bannedCards: List<String>) : PaymentEngine {
	override fun authorize(cardNo: String, amount: Double): Boolean {
		println("\tAuthorizing payment...")
		return if(bannedCards.contains(cardNo)) false else amount < 1000
    }
}
