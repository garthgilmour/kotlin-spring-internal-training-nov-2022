package numerals

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

fun convertToRoman(number: Int) = "Not done yet..."
