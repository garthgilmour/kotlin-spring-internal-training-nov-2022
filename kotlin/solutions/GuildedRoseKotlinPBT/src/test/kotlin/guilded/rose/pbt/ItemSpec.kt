package guilded.rose.pbt

import guilded.rose.Item
import guilded.rose.ItemType
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.*
import io.kotest.property.checkAll
import io.kotest.property.forAll

fun itemGenWithDays(maxDays: Int) = Arb.bind(
    Arb.enum<ItemType>(),
    Arb.int(1, maxDays),
    Arb.int(0, 50),
) { type, days, quality -> Item(type, days, quality) }

fun Item.endDays(num: Int) = repeat(num) { this.endOfDay() }

class ItemSpec : StringSpec({

    "quality is never negative" {
        val days = 10
        forAll(itemGenWithDays(days)) { item ->
            item.endDays(days + 1)
            item.quality() >= 0
        }
    }

    "quality never exceeds fifty" {
        forAll(itemGenWithDays(10)) { item ->
            item.endDays(100)
            item.quality() <= 50
        }
    }

    "legendary items never lose quality" {
        val days = 10
        forAll(Arb.int(0, 50)) { quality ->
            val item = Item(ItemType.Sulfuras, days, quality)
            item.endDays(days * 2)
            item.quality() == quality
        }
    }

    "cannot be created with negative quality" {
        fun valuesGen() = Arb.bind(
            Arb.enum<ItemType>(),
            Arb.positiveInts(),
            Arb.negativeInts(),
        ) { a, b, c -> Triple(a, b, c) }

        checkAll(valuesGen()) { (type, days, quality) ->
            shouldThrow<IllegalArgumentException> {
                Item(type, days, quality)
            }
        }
    }

    "cannot be created with excessive quality" {
        fun valuesGen() = Arb.bind(
            Arb.enum<ItemType>(),
            Arb.positiveInts(),
            Arb.int(51, 1000),
        ) { a, b, c -> Triple(a, b, c) }

        checkAll(valuesGen()) { (type, days, quality) ->
            shouldThrow<IllegalArgumentException> {
                Item(type, days, quality)
            }
        }
    }
})
