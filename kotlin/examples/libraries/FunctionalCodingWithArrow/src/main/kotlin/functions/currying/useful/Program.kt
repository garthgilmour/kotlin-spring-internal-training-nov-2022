package functions.currying.useful

import arrow.core.curried
import java.io.BufferedReader
import java.io.FileReader

val grep = { path: String, pattern: Regex, action: (String) -> Unit ->
    BufferedReader(FileReader(path)).use { reader ->
        reader.lines()
            .filter { pattern.matches(it) }
            .forEach(action)
    }
}

fun printLine() = println("-------------")

fun main() {
    val filePath = "input/functions/grepInput.txt"
    val regex1 = "[A-Z]{2}[0-9]{2}".toRegex()
    val regex2 = "[a-z]{2}[0-9]{2}".toRegex()

    val f1 = grep.curried()
    val grepInFile = f1(filePath)
    val grepRegex1 = grepInFile(regex1)
    val grepRegex2 = grepInFile(regex2)

    grepRegex1(::println)
    printLine()
    grepRegex2(::println)
}
