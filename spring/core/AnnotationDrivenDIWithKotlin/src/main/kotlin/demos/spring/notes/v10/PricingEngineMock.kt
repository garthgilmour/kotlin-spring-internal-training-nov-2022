package demos.spring.notes.v10

import demos.spring.notes.common.PricingEngine
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component("pricing")
class PricingEngineMock : PricingEngine {
    var prices: List<Double> = emptyList()
        @Value("#{dataSource.prices}")
        set(value) {
            field = value
        }
    var priceCount = -1
    var minimumDiscountAmount: Double = 0.0
        @Value("#{dataSource.discountAmount}")
        set(value) {
            field = value
        }
    private var percentageDiscount = 0
        @Value("#{dataSource.percentageDiscount * 2}")
        set(value) {
            field = value
        }

    override fun price(itemNo: String, quantity: Int): Double {
        if (++priceCount == prices.size) {
            priceCount = 0
        }
        var price = quantity * prices[priceCount]
        if (price >= minimumDiscountAmount) {
            price -= price * (percentageDiscount / 100)
        }
        return price
    }
}
