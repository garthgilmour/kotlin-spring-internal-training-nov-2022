package poker

import java.io.File

fun main() {
    fun readHands(fileName: String): List<Hand> {
        val file = File(fileName)
        return file.readLines()
                .filter(::checkHand)
                .map { Hand.build(it) }
    }

    val hands = readHands("input/pokerHands.txt")
    printHands(hands)
}

fun checkHand(line: String): Boolean {
    val cardRegex = "(?:(?:[2-9AQJK]|10)[HDSC])"
    //In PCRE we could use (?1) instead of a separate variable
    // But the Java regex library does not support this
    val pokerRegex = "^(?:$cardRegex ){4}$cardRegex\$".toRegex()
    val result = line.matches(pokerRegex)

    if (!result) {
        println("Ignoring: \"$line\"")
    }
    return result
}

fun printHands(hands: List<Hand>) {
    hands.forEach(::println)
}
