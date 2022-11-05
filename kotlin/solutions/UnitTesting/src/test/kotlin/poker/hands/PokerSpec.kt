package poker.hands

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class PokerSpec: ShouldSpec({
    listOf(
        "2D KS 7C 4D KC",
        "AD 7D 5S 6S 6H",
        "2S 6S AS 4S 4H",
        "7D QH KS 6D KH",
        "10D 4H 5C JS 5S",
        "AD JD JS 5C 3H"
    ).forEach { input ->
        should("recognise $input as single pair") {
            val hand = Hand.build(input)
            hand.name() shouldBe "Pair"
        }
    }

    listOf(
        "7D KS 7C 4D KC",
        "AD 7D 7S 6S 6H",
        "2S 6S 2S 4S 4H",
        "7D QH KS QD KH",
        "10D 4H 5C 4S 5S",
        "AD JD JS 5C 5H"
    ).forEach { input ->
        should("recognise $input as two pair") {
            val hand = Hand.build(input)
            hand.name() shouldBe "Two Pair"
        }
    }

    listOf(
        "2D KS 7C KD KC",
        "6D 7D 5S 6S 6H",
        "4D 6S AS 4S 4H",
        "KD QH KS 6D KH",
        "5D 4H 5C JS 5S",
        "AD JD JS 5C JH"
    ).forEach { input ->
        should("recognise $input as three of a kind") {
            val hand = Hand.build(input)
            hand.name() shouldBe "Three Of A Kind"
        }
    }

    listOf(
        "2D 3S 5C 4D 6C",
        "6D 7D 10S 9S 8H",
        "4D 6S 5S 7S 8H",
        "6D 3H 2S 4D 5H",
        "5D 6H 7C 8S 9S",
        "4D 8D 5S 6C 7H"
    ).forEach { input ->
        should("recognise $input as straight") {
            val hand = Hand.build(input)
            hand.name() shouldBe "Straight"
        }
    }

    listOf(
        "2D KD 7D 4D 6D",
        "AS JS 5S 6S 3S",
        "2H 6H AH 4H QH",
        "7C QC KC 6C 5C",
        "10S 4S 5S JS 2S",
        "AH JH 3H 9H 6H"
    ).forEach { input ->
        should("recognise $input as flush") {
            val hand = Hand.build(input)
            hand.name() shouldBe "Flush"
        }
    }

    listOf(
        "2D 2S 4C 4D 4H",
        "AD AH 5S 5C 5H",
        "6C 6S 6H 4S 4H",
        "7D 7H 7S 6D 6H",
        "10D 10H 10C JS JH",
        "AD AH JS JC JH"
    ).forEach { input ->
        should("recognise $input as a full house") {
            val hand = Hand.build(input)
            hand.name() shouldBe "Full House"
        }
    }

    listOf(
        "2D 2S 2C 2H KC",
        "AD AC AS 6S AH",
        "6S 6D AS 6C 6H",
        "QD QH QS QC KH",
        "10D 10H 10C JS 10S",
        "5D JD 5S 5C 5H"
    ).forEach { input ->
        should("recognise $input as four of a kind") {
            val hand = Hand.build(input)
            hand.name() shouldBe "Four Of A Kind"
        }
    }

    listOf(
        "KD QD JD 10D 9D",
        "2H 4H 3H 5H 6H",
        "7S JS 8S 10S 9S",
        "7C 6C 5C 4C 3C",
        "10D 9D 7D 8D 6D",
        "KS 9S QS JS 10S"
    ).forEach { input ->
        should("recognise $input as a straight flush") {
            val hand = Hand.build(input)
            hand.name() shouldBe "Straight Flush"
        }
    }

    listOf(
        "AD KD QD JD 10D",
        "KH QH JH 10H AH",
        "QS JS 10S AS KS",
        "JC 10C AC KC QC",
        "10D AD KD QD JD",
        "AS KS QS JS 10S"
    ).forEach { input ->
        should("recognise $input as a royal flush") {
            val hand = Hand.build(input)
            hand.name() shouldBe "Royal Flush"
        }
    }
})
