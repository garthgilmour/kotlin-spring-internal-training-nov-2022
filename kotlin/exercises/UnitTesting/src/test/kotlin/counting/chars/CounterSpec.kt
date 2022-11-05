package counting.chars

import counting.chars.process
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.shouldBe

class CounterSpec: StringSpec({
    "recognises a sequence of the same letter" {
        val result = process("aaa")
        result.size shouldBe 1
        result.shouldContain(Pair('a', 3))
    }

    "recognises sequences of different letters" {
        val result = process("aaabbbbcc")
        result.size shouldBe 3
        result.shouldContainInOrder(Pair('a', 3), Pair('b', 4), Pair('c', 2))
    }

    "recognises multiple sequences of the same letter" {
        val result = process("aaaddddbbbbccdd")
        result.size shouldBe 5
        result.shouldContainInOrder(
            Pair('a', 3),
            Pair('d', 4),
            Pair('b', 4),
            Pair('c', 2),
            Pair('d', 2))
    }
})
