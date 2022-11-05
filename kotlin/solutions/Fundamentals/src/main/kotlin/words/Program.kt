package words

import arrow.core.Either
import java.io.File
import java.io.FileNotFoundException

const val filePath = "./input/uniqueWords.txt"

fun main() {
    readLinesFromFile().fold(::showError, ::countWords)
}

fun showError(problem: String) = println("Program failed because '$problem'")

fun countWords(lines: List<String>) {
    fun convertToPair(entry: Map.Entry<String, Int>) = Pair(entry.key, entry.value)
    fun buildComparator() = Comparator.comparingInt { pair: Pair<String, Int> -> pair.second }

    return buildTabularResults(lines)
        .entries
        .map(::convertToPair)
        .sortedWith(buildComparator())
        .reversed()
        .map { pair -> "'${pair.first}' occurs ${pair.second} times" }
        .forEach(::println)
}

fun readLinesFromFile() = try {
    Either.Right(File(filePath).readLines())
} catch(ex: FileNotFoundException) {
    Either.Left(ex.message ?: "unknown error")
}

fun buildTabularResults(lines: List<String>): Map<String, Int> {
    fun splitLineIntoWords(line: String) = line.split("\\s+".toRegex())
    fun normalizeWord(word: String) = word.lowercase().replace("\\W".toRegex(), "")
    fun isNotNumber(word: String) = !word.matches("^[0-9]+$".toRegex())

    return lines
        .flatMap(::splitLineIntoWords)
        .map(::normalizeWord)
        .filter(::isNotNumber)
        .groupBy { word -> word }
        .mapValues { entry -> entry.value.size }
        .filter { entry -> entry.value > 1 }
}

