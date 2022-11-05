package counting.chars.pbt.custom

import counting.chars.pbt.process
import io.kotest.core.spec.style.StringSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.*
import io.kotest.property.forAll

fun countUniqueChars(input: String): Int {
    val regex = """(.)(?!\1)""".toRegex()
    return regex.findAll(input).toList().size
}

fun buildSampleDataGen(): Arb<String> {
    val ranges = listOf('a'..'z', 'A'..'Z')
    val repeatedCharGen = Arb.char(ranges).flatMap { char ->
        Arb.int(1..10).map { num ->
            char.toString().repeat(num)
        }
    }
    val listOfRepeatedCharGen = Arb.list(repeatedCharGen, 0..100)

    return listOfRepeatedCharGen.map { it.joinToString(separator = "") }
}

class CounterSpec : StringSpec({
    "length of string equals total of counts in pairs" {
        forAll(buildSampleDataGen()) { str ->
            val result = process(str)

            val strLength = str.length
            val totalOfPairs = result.sumOf { it.second }

            strLength == totalOfPairs
        }
    }

    "length of results equals number of unique letters" {
        forAll(buildSampleDataGen()) { str ->
            val result = process(str)

            result.size == countUniqueChars(str)
        }
    }
})
