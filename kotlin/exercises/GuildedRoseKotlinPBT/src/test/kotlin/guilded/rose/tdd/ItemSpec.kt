package guilded.rose.tdd

import guilded.rose.Item
import guilded.rose.ItemType.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class ItemSpec: ShouldSpec({
    val checkQuality = { item: Item, qualityValues: List<Int> ->
        qualityValues.forEach { value ->
            item.endOfDay()
            value shouldBe item.quality()
        }
    }
    context("on creation") {
        should("initially have the given quality") {
            val item = Item(Regular, 4, 10)
            item.quality() shouldBe 10
        }

        should("supplied quality cannot be negative") {
            shouldThrow<IllegalArgumentException> {
                Item(Regular, 4, -1)
            }
        }

        should("supplied quality cannot exceed fifty") {
            shouldThrow<IllegalArgumentException> {
                Item(Regular, 4, 51)
            }
        }
    }
    should("decrease in quality to zero") {
        val item = Item(Regular, 4, 3)
        checkQuality(item, listOf(2, 1, 0))
    }

    should("never decrease in quality below zero") {
        val item = Item(Regular, 4, 2)
        checkQuality(item, listOf(1, 0, 0))
    }

    context("regular items") {
        should("degrade twice as fast after expiry") {
            val item = Item(Regular, 4, 10)
            checkQuality(item, listOf(9, 8, 7, 6, 4, 2, 0))
        }
    }

    context("Aged Brie") {
        should("increase in quality") {
            val item = Item(AgedBrie, 3, 10)
            checkQuality(item, listOf(11, 12, 13, 14, 15))
        }

        should("never have a quality over fifty") {
            val item = Item(AgedBrie, 3, 48)
            checkQuality(item, listOf(49, 50, 50, 50, 50))
        }
    }

    context("Sulfuras") {
        should("never change in quality") {
            val item = Item(Sulfuras, 3, 30)
            checkQuality(item, listOf(30, 30, 30, 30, 30))
        }
    }

    context("Backstage Passes") {
        should("increase in value when ten days remain") {
            val item = Item(BackstagePass, 12, 20)
            checkQuality(item, listOf(21, 23, 25, 27))
        }

        should("increase in value when five days remain") {
            val item = Item(BackstagePass, 8, 20)
            checkQuality(item, listOf(22, 24, 27, 30))
        }

        should("lose all value after the concert") {
            val item = Item(BackstagePass, 3, 20)
            checkQuality(item, listOf(23, 26, 29, 0, 0, 0))
        }
    }
})

