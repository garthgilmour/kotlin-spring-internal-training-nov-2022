package poker.hands

import poker.hands.Rank.Companion.allRanks
import poker.hands.Rank.Companion.highRanks

class Hand(private val hand: List<Card>) {
    companion object {
        fun build(line: String) = Hand(line.split(" ").map { Card.build(it) })
    }

    override fun toString() = hand.joinToString(
            prefix = "[ ",
            postfix = " ]\t${name()}",
            separator = " ")

    fun name() = when {
        isRoyalFlush()    -> "Royal Flush"
        isStraightFlush() -> "Straight Flush"
        isFourOfAKind()   -> "Four Of A Kind"
        isFullHouse()     -> "Full House"
        isFlush()         -> "Flush"
        isStraight()      -> "Straight"
        isThreeOfAKind()  -> "Three Of A Kind"
        isTwoPair()       -> "Two Pair"
        isPair()          -> "Pair"
        else              -> "Highest Card"
    }

    private fun isFlush()         = false
    private fun isStraight()      = false
    private fun isRoyalFlush()    = false
    private fun isStraightFlush() = false
    private fun isFullHouse()     = false
    private fun isFourOfAKind()   = false
    private fun isThreeOfAKind()  = false
    private fun isTwoPair()       = false
    private fun isPair()          = false
}
