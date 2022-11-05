package imperial.currency

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class AmountSpec : ShouldSpec() {
    override fun isolationMode() = IsolationMode.InstancePerTest

    private var amount: Amount = Amount(0)

    init {
        should("handle a zero amount") {
            amount.toString() shouldBe "May God bless you"
        }
        should("handle a single penny") {
            amount.add(Unit.PENNY)
            amount.toString() shouldBe "1 penny"
        }
    }
}
