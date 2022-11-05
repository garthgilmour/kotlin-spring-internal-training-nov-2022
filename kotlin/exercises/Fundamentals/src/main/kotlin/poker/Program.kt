package poker

import java.io.File

fun main() {
    val file = File("input/pokerHands.txt")
    file.readLines().forEach(::println)
}
