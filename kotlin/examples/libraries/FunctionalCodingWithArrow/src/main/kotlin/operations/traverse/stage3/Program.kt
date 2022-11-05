package operations.traverse.stage3;

import arrow.core.Either
import arrow.core.Either.*
import arrow.core.computations.either
import arrow.core.traverseEither
import java.nio.file.Files
import java.nio.file.NoSuchFileException
import java.nio.file.Paths
import java.util.stream.Collectors.toList

fun propertyViaJVM(key: String): Either<String, String> {
    val result = System.getProperty(key)
    return if (result != null) {
        Right(result)
    } else {
        Left("No JVM property: $key")
    }
}

fun propertyViaFile(path: String, key: String): Either<String, String> = try {
    val propertyValue = { str: String -> str.substring(str.indexOf("=") + 1) }
    val lines = Files.lines(Paths.get("input/traverse", path))
    lines
        .filter { it.startsWith("$key=") }
        .findFirst()
        .map(propertyValue)
        .map { Right(it) as Either<String, String> }
        .orElse(Left("No File property: $key"))
} catch (ex: NoSuchFileException) {
    Left("No properties file: $path")
}

fun readAllLines(path: String): Either<String, List<String>> = try {
    Right(
        Files.lines(Paths.get("input/traverse", path))
            .collect(toList())
    )
} catch (ex: NoSuchFileException) {
    Left("No file called $path")
}

suspend fun demoTraverse(data: List<String>) {
    val findViaJVM = { input: List<String> -> input.traverseEither(::propertyViaJVM) }

    val findViaFile = { input: List<String> ->
        val file = "stage3.properties"
        input.traverseEither {
            propertyViaFile(file, it)
        }
    }

    val readEverything = { paths: List<String> -> paths.traverseEither(::readAllLines) }

    val result = either<String, List<String>> {
        val a = findViaJVM(data).bind()
        val b = findViaFile(a).bind()
        val c = readEverything(b).bind()
        c.flatten()
    }

    result.fold(
        { error -> println("The process failed!\n\t$error") },
        { lines ->
            println("The process was successful:")
            lines.map { "\t$it" }
                .forEach(::println)
        }
    )
}

suspend fun main() {
    System.setProperty("cagney", "cagney.lacy")
    System.setProperty("starsky", "starsky.hutch")
    System.setProperty("hart", "hart.hart")

    demoTraverse(listOf("cagney", "starsky", "hart"))
}
