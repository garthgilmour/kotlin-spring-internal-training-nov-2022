package imperial.currency

class Amount(private var amount: Int) {
    fun add(newAmount: Unit) {
        amount += newAmount.value
    }

    override fun toString(): String {
        return if (amount == 0) "Nothing" else value()
    }

    private fun value(): String {
        val builder = StringBuilder()
        convert(amount, 0, Unit.values(), builder)
        return builder.toString()
    }

    private fun convert(
        value: Int,
        index: Int,
        units: Array<Unit>,
        builder: java.lang.StringBuilder
    ) {
        fun chooseLabel(num: Int, unit: Unit) = if (num == 1) unit.singular else unit.plural
        fun addSpaceIfNeeded() {
            if (builder.isNotEmpty()) {
                builder.append(" ")
            }
        }

        val unit = units[index]
        val times = value / unit.value
        val remainder = value % unit.value

        if (times > 0) {
            addSpaceIfNeeded()
            builder.append("$times ${chooseLabel(times, unit)}")
        }
        if (remainder > 0) {
            convert(remainder, index + 1, units, builder)
        }
    }
}
