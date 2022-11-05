package guilded.rose.pbt

import guilded.rose.Item
import guilded.rose.ItemType
import io.kotest.core.spec.style.StringSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.*
import io.kotest.property.forAll

class ItemSpec : StringSpec({

    "quality is never negative" {
        forAll(Arb.enum<ItemType>(),
            Arb.int(1, 10),
            Arb.int(0, 50)) { type, days, quality ->

            val item = Item(type, days, quality)
            repeat(days + 1) { item.endOfDay() }
            item.quality() >= 0
        }
    }
})
