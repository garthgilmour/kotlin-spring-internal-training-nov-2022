package demos.spring.notes.v0

class PaymentEngine(serverUrl: String?) {
    fun authorize(cardNo: String?, amount: Double): Boolean {
        return amount < 1000
    }
}
