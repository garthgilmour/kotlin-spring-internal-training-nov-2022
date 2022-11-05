package counting.chars

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly

class CounterSpec: StringSpec({
    "recognises a sequence of the same letter" {
        val result = process("aaa")
        result.shouldContainExactly(Pair('a', 3))
    }

    "recognises sequences of different letters" {
        val result = process("aaabbbbcc")
        result.shouldContainExactly(Pair('a', 3), Pair('b', 4), Pair('c', 2))
    }

    "recognises multiple sequences of the same letter" {
        val result = process("aaaddddbbbbccdd")
        result.shouldContainExactly(
            Pair('a', 3),
            Pair('d', 4),
            Pair('b', 4),
            Pair('c', 2),
            Pair('d', 2))
    }
})
