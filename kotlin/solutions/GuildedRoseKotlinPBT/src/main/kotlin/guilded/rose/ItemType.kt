package guilded.rose

enum class ItemType(private val newQuality: (Int, Int) -> Int) {
    Regular({ days, quality ->
        when {
            quality == 0 -> 0
            days < 0 -> if(quality >= 2) quality - 2 else quality - 1
            else -> quality - 1
        }
    }),
    AgedBrie({ _, quality ->
        if (quality == 50) 50 else quality + 1
    }),
    Sulfuras({ _, quality -> quality }),
    BackstagePass({ days, quality ->
        when {
            days < 0 -> 0
            days <= 5 -> quality + 3
            days <= 10 -> quality + 2
            else -> quality + 1
        }
    });

    fun apply(days: Int, oldQuality: Int) = newQuality(days, oldQuality)
}

