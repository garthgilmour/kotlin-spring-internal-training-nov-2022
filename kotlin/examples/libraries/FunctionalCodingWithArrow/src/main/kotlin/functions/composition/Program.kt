package functions.composition

import arrow.core.andThen
import arrow.core.compose
import java.io.BufferedReader
import java.io.FileReader
import java.util.stream.Collectors

val source = { name: String -> "input/functions/$name" }

val allLines = { path: String ->
    val reader = BufferedReader(FileReader(path))
    reader.use {
        it.lines().collect(Collectors.toList())
    }
}

val findMatches = { input: List<String> ->
    val regex = "[A-Z]{2}[0-9]{2}".toRegex()
    input.filter(regex::matches)
}

fun main() {
    val grep1 = findMatches compose allLines compose source
    println(grep1("grepInput.txt"))

    val grep2 = source andThen allLines andThen findMatches
    println(grep2("grepInput.txt"))
}
