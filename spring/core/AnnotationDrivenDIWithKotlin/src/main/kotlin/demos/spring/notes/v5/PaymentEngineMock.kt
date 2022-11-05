package demos.spring.notes.v5

import demos.spring.notes.common.PaymentEngine

class PaymentEngineMock : PaymentEngine {
    override fun authorize(cardNo: String, amount: Double): Boolean {
        return amount < 1000
    }
}
