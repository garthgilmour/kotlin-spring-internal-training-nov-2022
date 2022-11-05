package videostore

import videostore.PriceCode.CHILDRENS
import videostore.PriceCode.NEW_RELEASE
import videostore.PriceCode.REGULAR

class Movie(val title: String, private val priceCode: PriceCode) {
    fun cost(daysRented: Int) = when (priceCode) {
        REGULAR -> {
            val extra = if (daysRented > 2) (daysRented - 2) * 1.5 else 0.0
            2.0 + extra
        }
        NEW_RELEASE -> (daysRented * 3).toDouble()
        CHILDRENS -> {
            val extra = if (daysRented > 3) (daysRented - 3) * 1.5 else 0.0
            1.5 + extra
        }
    }

    fun points(daysRented: Int) = if (priceCode == NEW_RELEASE && daysRented > 1) 2 else 1
}
