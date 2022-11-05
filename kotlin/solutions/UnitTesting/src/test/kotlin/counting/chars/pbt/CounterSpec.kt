package counting.chars.pbt

import io.kotest.core.spec.style.StringSpec
import io.kotest.property.forAll

fun countUniqueChars(input: String): Int {
    val regex = """(.)(?!\1)""".toRegex()
    return regex.findAll(input).toList().size
}

class CounterSpec: StringSpec({
    "length of string equals total of counts in pairs" {
        forAll<String> { str ->
            val result = process(str)

            val strLength = str.length
            val totalOfPairs = result.sumOf { it.second }

            strLength == totalOfPairs
        }
    }

    "length of results equals number of unique letters" {
        forAll<String> { str ->
            val result = process(str)

            result.size == countUniqueChars(str)
        }
    }
})
