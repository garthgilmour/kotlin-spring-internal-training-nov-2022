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

        should("handle Pennies to 11") {
            amount.add(PENNY)
            amount.toString() shouldBe "1 penny"

            (2..11).forEach { num ->
                amount.add(PENNY)
                amount.toString() shouldBe "$num pennies"
            }
        }

        should("recognise 12 Pennies to the Shilling") {
            repeat(12) { amount.add(PENNY) }
            amount.toString() shouldBe "1 shilling"
        }

        should("handle Shillings to 19") {
            amount.add(SHILLING)
            amount.toString() shouldBe "1 shilling"

            (2..19).forEach { num ->
                amount.add(SHILLING)
                amount.toString() shouldBe "$num shillings"
            }
        }

        should("recognise 20 Shillings to the Pound") {
            repeat(20) { amount.add(SHILLING) }
            amount.toString() shouldBe "1 pound"
        }

        should("recognise 21 Shillings to the Guinea") {
            repeat(21) { amount.add(SHILLING) }
            amount.toString() shouldBe "1 guinea"
        }

        should("handle Guineas") {
            amount.add(GUINEA)
            amount.toString() shouldBe "1 guinea"

            val arbitraryMax = 10
            (2..arbitraryMax).forEach { num ->
                amount.add(GUINEA)
                amount.toString() shouldBe "$num guineas"
            }
        }

        should("handle Guineas and Pounds") {
            repeat(2) { amount.add(GUINEA) }
            amount.add(POUND)

            amount.toString() shouldBe "2 guineas 1 pound"
        }

        should("handle Guineas and Shillings") {
            repeat(2) { amount.add(GUINEA) }
            repeat(3) { amount.add(SHILLING) }

            amount.toString() shouldBe "2 guineas 3 shillings"
        }

        should("handle adding Shillings to Pounds") {
            amount.add(POUND)
            repeat(3) { amount.add(SHILLING) }
            amount.toString() shouldBe "1 guinea 2 shillings"
        }

        should("handle adding Pennies to Shillings") {
            repeat(19) { amount.add(SHILLING) }
            repeat(23) { amount.add(PENNY) }
            amount.toString() shouldBe "1 pound 11 pennies"
        }

        should("handle mixed amounts") {
            repeat(100) {
                amount.add(POUND)
                amount.add(PENNY)
                amount.add(GUINEA)
                amount.add(SHILLING)
            }
            amount.toString() shouldBe "200 guineas 8 shillings 4 pennies"
        }
    }
}
