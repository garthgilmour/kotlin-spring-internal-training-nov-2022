package functions.partial.application.useful

import arrow.core.partially2
import arrow.core.partially3
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
    val regex = "[A-Z]{2}[0-9]{2}".toRegex()

    grep(filePath, regex, ::println)
    printLine()

    val grepAndPrint = grep.partially3(::println)
    grepAndPrint(filePath, regex)
    printLine()

    val sb = StringBuilder()
    val grepAndConcat = grep.partially3 {sb.append(it)}
    grepAndConcat(filePath, regex)
    println(sb.toString())
    printLine()

    val grepAndPrintRegex = grepAndPrint.partially2(regex)
    grepAndPrintRegex(filePath)
    printLine()
}
