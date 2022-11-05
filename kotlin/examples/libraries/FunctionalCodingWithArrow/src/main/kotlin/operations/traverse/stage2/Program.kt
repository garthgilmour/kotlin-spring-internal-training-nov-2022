package operations.traverse.stage2

import arrow.core.Either
import arrow.core.Either.*
import arrow.core.traverseEither
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.NoSuchFileException

fun propertyViaFile(path: String, name: String): Either<String, String> = try {
    val propertyValue = { str: String -> str.substring(str.indexOf("=") + 1)}
    val lines = Files.lines(Paths.get("input/traverse",path))
    lines
        .filter { it.startsWith("$name=") }
        .findFirst()
        .map (propertyValue)
        .map { Right(it) as Either<String, String> }
        .orElse(Left("No property called $name"))
} catch(ex: NoSuchFileException) {
    Left("No file called $path")
}

fun demoTraverse(fileName: String, input: List<String>) {
    val failure = { error: String -> println(error) }

    val success = { results: List<String> ->
        println("Results are:")
        results.map { s -> "\t$s" }
            .forEach(::println)
    }

    val readInFile = { name: String -> propertyViaFile(fileName, name) }

    val result = input.traverseEither(readInFile)

    result.fold(failure, success)
}

fun main() {
    val correctName = "stage2.properties"
    val incorrectName = "false.properties"

    demoTraverse(correctName, listOf("foo", "bar", "zed"))
    demoTraverse(correctName, listOf("false", "bar", "zed"))
    demoTraverse(correctName, listOf("foo", "false", "zed"))
    demoTraverse(correctName ,listOf("foo", "bar", "false"))
    demoTraverse(incorrectName ,listOf("foo", "bar", "false"))
}
