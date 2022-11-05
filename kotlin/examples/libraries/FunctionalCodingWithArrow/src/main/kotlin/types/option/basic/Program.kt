package types.option.basic

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import arrow.core.getOrElse

fun readPropertyA(name: String): Option<String> {
    val result = System.getProperty(name)
    return if(result != null) Some(result) else None
}

fun readPropertyB(name: String) = Option.fromNullable(System.getProperty(name))

fun print1(input: Option<String>): Unit {
    when(input) {
        is None -> println("Nothing was found")
        is Some -> println("'${input}' was found")
    }
}

fun print2(input: Option<String>): Unit {
    println("${input.getOrElse { "Nothing" }} was found")
}

fun print3(input: Option<String>): Unit {
    val result = input.fold({ "Nothing" }, { it })
    println("$result was found")
}

fun print4(input1: Option<String>, input2: Option<String>): Unit {
    val result = input1.flatMap { first ->
        input2.map { second ->
            "$first and $second"
        }
    }
    println("Results are ${result.getOrElse { "Nothing" }}")
}

fun printLine() = println("---------------")

fun main() {
    print1(readPropertyA("java.version"))
    print1(readPropertyA("wibble"))
    printLine()
    print2(readPropertyB("java.version"))
    print2(readPropertyB("wibble"))
    printLine()
    print3(readPropertyA("java.version"))
    print3(readPropertyA("wibble"))
    printLine()
    print4(
        readPropertyA("java.version"),
        readPropertyB("java.version")
    )
    printLine()
    print4(
        readPropertyA("java.version"),
        readPropertyB("wibble")
    )
    printLine()
    print4(
        readPropertyA("wibble"),
        readPropertyB("wibble")
    )
    printLine()
    print4(
        readPropertyA("wibble"),
        readPropertyB("wibble")
    )
}
