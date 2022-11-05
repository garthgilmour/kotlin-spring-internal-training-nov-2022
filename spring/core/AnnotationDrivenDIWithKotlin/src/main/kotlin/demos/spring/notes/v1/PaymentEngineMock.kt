package demos.spring.notes.v1

import demos.spring.notes.common.PaymentEngine
import org.springframework.stereotype.Component

@Component
class PaymentEngineMock : PaymentEngine {
    override fun authorize(cardNo: String, amount: Double): Boolean {
        return amount < 1000
    }
}
