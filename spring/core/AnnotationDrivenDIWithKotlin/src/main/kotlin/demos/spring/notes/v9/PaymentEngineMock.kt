package demos.spring.notes.v9

import demos.spring.notes.common.PaymentEngine
import org.springframework.stereotype.Component

@Component("payment")
class PaymentEngineMock : PaymentEngine {
    override fun authorize(cardNo: String, amount: Double): Boolean {
        return amount < 1000
    }
}
