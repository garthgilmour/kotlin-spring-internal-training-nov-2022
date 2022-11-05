package poker.hands

class Card(val rank: Rank, val suit: Suit) {
    companion object {
        fun build(input: String): Card {
            val tenCard = input.startsWith("10")
            val rankStr = if (tenCard) "_10" else "_${input.substring(0, 1)}"
            val suitStr = input.substring(if (tenCard) 2 else 1)

            return Card(
                Rank.valueOf(rankStr),
                Suit.valueOf(suitStr)
            )
        }
    }

    override fun toString(): String {
        return "$rank$suit"
    }
}
