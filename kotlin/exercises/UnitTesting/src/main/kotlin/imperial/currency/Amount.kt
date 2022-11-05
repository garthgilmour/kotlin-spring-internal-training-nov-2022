package imperial.currency

class Amount(private var amount: Int) {
    fun add(newAmount: Unit) {
        amount += newAmount.value
    }

    override fun toString(): String {
        return if (amount == 0) "Nothing" else ""
    }
}

