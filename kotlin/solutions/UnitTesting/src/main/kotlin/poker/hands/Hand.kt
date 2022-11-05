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

    private fun nOf(count: Int, rank: Rank) = hand.count { it.rank == rank } >= count
    private fun twoOf(rank: Rank)   = nOf(2, rank)
    private fun threeOf(rank: Rank) = nOf(3, rank)
    private fun fourOf(rank: Rank)  = nOf(4, rank)

    private fun allOfSameSuit() = hand.all { it.suit == hand[0].suit }
    private fun allCardsHigh() = hand.all { card -> highRanks.any { card.rank == it } }

    private fun ranksInDescendingOrder(): List<Rank> {
        val comparator = Comparator<Rank> { a, b ->
            if (allRanks.indexOf(a) < allRanks.indexOf(b)) -1 else 1
        }

        return hand.map { it.rank }.sortedWith(comparator)
    }

    private fun isFlush()         = allOfSameSuit()
    private fun isRoyalFlush()    = allOfSameSuit() && allCardsHigh()
    private fun isStraightFlush() = isStraight() && allOfSameSuit()
    private fun isFourOfAKind()   = allRanks.any(::fourOf)
    private fun isThreeOfAKind()  = allRanks.any(::threeOf)
    private fun isTwoPair()       = pairsOfRanks().any { pair -> twoOf(pair.first) && twoOf(pair.second) }
    private fun isPair()          = allRanks.any(::twoOf)

    private fun isStraight(): Boolean {
        val allRanksAsStr = allRanks.joinToString(separator = "")
        val handRanksAsStr = ranksInDescendingOrder().joinToString(separator = "")

        return allRanksAsStr.contains(handRanksAsStr)
    }

    private fun isFullHouse(): Boolean {
        return pairsOfRanks().any { pair -> twoOf(pair.first) && threeOf(pair.second) }
    }

    private fun pairsOfRanks(): List<Pair<Rank, Rank>> {
        val results = mutableListOf<Pair<Rank, Rank>>()
        allRanks.forEach { rank1 ->
            allRanks.forEach { rank2 ->
                if (rank1 !== rank2) {
                    results.add(Pair(rank1, rank2))
                }
            }
        }
        return results
    }
}
