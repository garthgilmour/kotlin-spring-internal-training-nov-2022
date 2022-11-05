package imperial.currency

enum class Unit(val value: Int,
                val singular: String,
                val plural: String) {
    GUINEA(252, "guinea", "guineas"),
    POUND(240, "pound", "pounds"),
    SHILLING(12, "shilling", "shillings"),
    PENNY(1, "penny", "pennies")
}
