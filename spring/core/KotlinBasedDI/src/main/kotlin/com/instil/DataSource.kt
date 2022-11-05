package com.instil

object DataSource {
	fun buildBannedCards() = listOf("RST456GHI78", "UVW456GHI78", "XYZ456GHI78")
	fun discountAmount() = 500
	fun percentageDiscount() = 10
	fun prices() = listOf(1.20, 3.40, 5.60)
	fun buildStockData() = mapOf(Pair("ABC123",10),Pair("DEF456",20),Pair("GHI789",30))
}
