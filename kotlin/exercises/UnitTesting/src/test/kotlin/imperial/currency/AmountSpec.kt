package imperial.currency

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

import imperial.currency.Unit.*

class AmountSpec : ShouldSpec() {
    private val amount: Amount = Amount(0)

    init {
        isolationMode = IsolationMode.InstancePerTest

        should("handle a zero amount") {
            amount.toString() shouldBe "Nothing"
        }

        should("handle adding pennies") {
            amount.add(PENNY)
            amount.toString() shouldBe "1 penny"
            amount.add(PENNY)
            amount.toString() shouldBe "2 pennies"
        }
    }
}
