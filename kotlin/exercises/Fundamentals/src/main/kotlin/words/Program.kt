package words

import arrow.core.Either
import java.io.File
import java.io.FileNotFoundException

const val filePath = "./input/uniqueWords.txt"

fun main() {
    readLinesFromFile().fold(::showError, ::countWords)
}

fun countWords(list: List<String>) {
    list.forEach(::println)
}

fun showError(problem: String) = println("Program failed because '$problem'")

fun readLinesFromFile() = try {
    Either.Right(File(filePath).readLines())
} catch(ex: FileNotFoundException) {
    Either.Left(ex.message ?: "unknown error")
}
