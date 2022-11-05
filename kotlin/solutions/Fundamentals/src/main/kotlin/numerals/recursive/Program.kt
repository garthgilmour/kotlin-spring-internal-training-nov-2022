package numerals.recursive

val numerals = listOf(Pair("C", 100),
        Pair("XC", 90),
        Pair("L", 50),
        Pair("XL", 40),
        Pair("X", 10),
        Pair("IX", 9),
        Pair("V", 5),
        Pair("IV", 4),
        Pair("I", 1))

fun main() {
    println("Enter a number...")
    val number = readLine()?.toInt() ?: 0

    val result = convertToRoman(number)
    println("$number as a roman numeral is $result")
}

fun convertToRoman(number: Int): String {
    fun convert(value: Int, index: Int): String {
        if (value > 0) {
            val current = numerals[index]
            val times = value / current.second
            val remainder = value % current.second

            val result = StringBuilder()
            repeat(times) { result.append(current.first) }

            return result.toString() + convert(remainder, index + 1)
        }
        return ""
    }
    return if (number == 0) "Nulla" else convert(number, 0)
}
