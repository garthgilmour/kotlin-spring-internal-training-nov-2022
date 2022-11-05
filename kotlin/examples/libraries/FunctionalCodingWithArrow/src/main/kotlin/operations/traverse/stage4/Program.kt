package operations.traverse.stage4;

import arrow.core.Validated
import arrow.core.Validated.*
import arrow.core.computations.either
import arrow.core.traverseValidated
import arrow.typeclasses.Semigroup

import java.nio.file.Files
import java.nio.file.NoSuchFileException
import java.nio.file.Paths
import java.util.stream.Collectors.toList

fun propertyViaJVM(key: String): Validated<String, String> {
    val result = System.getProperty(key)
    return if (result != null) {
        Valid(result)
    } else {
        Invalid("No JVM property: $key")
    }
}

fun propertyViaFile(path: String, key: String): Validated<String, String> = try {
    val propertyValue = { str: String -> str.substring(str.indexOf("=") + 1) }
    val lines = Files.lines(Paths.get("input/traverse", path))
    lines
        .filter { it.startsWith("$key=") }
        .findFirst()
        .map(propertyValue)
        .map { Valid(it) as Validated<String, String> }
        .orElse(Invalid("No File property: $key"))
} catch (ex: NoSuchFileException) {
    Invalid("No properties file: $path")
}

fun readAllLines(path: String): Validated<String, List<String>> = try {
    Valid(
        Files.lines(Paths.get("input/traverse", path))
            .collect(toList())
    )
} catch (ex: NoSuchFileException) {
    Invalid("No file called $path")
}

suspend fun demoTraverse(data: List<String>) {
    val findViaJVM = { input: List<String> -> input.traverseValidated(Semigroup.string(),::propertyViaJVM) }

    val findViaFile = { input: List<String> ->
        val file = "stage3.properties"
        input.traverseValidated(Semigroup.string()) {
            propertyViaFile(file, it)
        }
    }

    val readEverything = { paths: List<String> -> paths.traverseValidated(Semigroup.string(),::readAllLines) }

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
