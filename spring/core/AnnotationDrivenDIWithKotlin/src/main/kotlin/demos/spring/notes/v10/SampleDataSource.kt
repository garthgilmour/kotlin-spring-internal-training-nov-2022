package demos.spring.notes.v10

import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.set


@Component("dataSource")
class SampleDataSource {
    val discountAmount: Int
        get() = 500

    val percentageDiscount: Int
        get() = 10

    val prices: List<Double>
        get() {
            val list = ArrayList<Double>()
            list.add(1.20)
            list.add(3.40)
            list.add(5.60)
            return list
        }

    fun buildStockData(): Map<String, Int> {
        val stockData = java.util.HashMap<String, Int>()
        stockData["ABC123"] = 10
        stockData["DEF456"] = 20
        stockData["GHI789"] = 30
        return stockData
    }

    val openDays: String
        get() = "Monday Tuesday Thursday Saturday"

    val CEO: String
        get() = "Steve Jobs"

    companion object {
        @JvmStatic
        fun buildBannedCards(): List<String> {
            val list = ArrayList<String>()
            list.add("RST456GHI78")
            list.add("UVW456GHI78")
            list.add("XYZ456GHI78")
            return list
        }
    }
}


