package rochambeau.enums

enum class Rochambeau {
    ROCK, PAPER, SCISSORS;

    fun beats(other: Rochambeau): Boolean {
        return when (this) {
            ROCK -> other == SCISSORS
            PAPER -> other == ROCK
            SCISSORS -> other == PAPER
        }
    }
}
