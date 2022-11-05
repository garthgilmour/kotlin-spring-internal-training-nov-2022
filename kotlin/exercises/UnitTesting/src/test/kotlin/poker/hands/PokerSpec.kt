package poker.hands

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class PokerSpec: ShouldSpec({
    should("recognise a poor hand") {
        val hand = Hand.build("2D KS 7C 4D QC")
        hand.name() shouldBe "Highest Card"
    }

    should("recognise a single pair") {
        val hand = Hand.build("2D KS 7C 4D KC")
        hand.name() shouldBe "Pair"
    }
})
