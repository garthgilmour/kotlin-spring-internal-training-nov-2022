package guilded.rose

class Item(private val type: ItemType,
           private var sellIn: Int,
           private var quality: Int) {

    init {
        require(quality >= 0) { "Quality cannot be negative" }
        require(quality <= 50) { "Quality cannot exceed fifty" }
    }

    fun quality() = quality

    fun endOfDay() {
        //Use -1 to signify expired
        if (sellIn >= 0) {
            sellIn--
        }
        quality = type.apply(sellIn, quality)
    }

    override fun toString() = "$type item of $quality quality with $sellIn days to sell"
}
