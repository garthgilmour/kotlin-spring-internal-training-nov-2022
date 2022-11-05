package poker

enum class Rank(val isHigh: Boolean = false) {
    _A(true),
    _Q(true),
    _K(true),
    _J(true),
    _10(true),
    _9,
    _8,
    _7,
    _6,
    _5,
    _4,
    _3,
    _2;

    companion object {
        val allRanks = values()
        val highRanks = values().filter { it.isHigh }
    }

    override fun toString() = super.toString().drop(1)
}
