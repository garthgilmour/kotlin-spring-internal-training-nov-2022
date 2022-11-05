package demos.spring.notes.v10

import demos.spring.notes.common.PaymentEngine
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component("payment")
class PaymentEngineMock @Autowired constructor(
        @Value("#{T(demos.spring.notes.v10.SampleDataSource).buildBannedCards()}")
        private val bannedCards: List<String>) : PaymentEngine {

    override fun authorize(cardNo: String, amount: Double): Boolean {
        return if (bannedCards.contains(cardNo)) {
            false
        } else amount < 1000
    }
}
