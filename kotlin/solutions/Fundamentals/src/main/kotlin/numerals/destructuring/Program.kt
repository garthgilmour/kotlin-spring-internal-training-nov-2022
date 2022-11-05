package numerals.destructuring

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
    if (number == 0) return "Nulla"

    val result = StringBuilder()
    var value = number
    var index = 0

    do {
        val(roman, decimal) = numerals[index]
        val times = value / decimal

        repeat(times) { result.append(roman) }
        value %= decimal

        index++
    } while (value > 0)
    return result.toString()
}
