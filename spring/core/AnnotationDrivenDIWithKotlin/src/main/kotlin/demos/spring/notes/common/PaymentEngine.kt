package demos.spring.notes.common

interface PaymentEngine {
    fun authorize(cardNo: String, amount: Double): Boolean
}
