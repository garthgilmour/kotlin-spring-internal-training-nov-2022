package types.either

import arrow.core.*
import arrow.core.Either.*
import java.lang.Math.random

fun genNumber() : Either<Int, Int> {
    val number = (random() * 100).toInt()
    return if(number % 2 == 0) Right(number) else Left(number)
}

fun main(args: Array<String>) {
    val results = (1 .. 10).map {
        genNumber().flatMap { first ->
            genNumber().map { second ->
                Pair(first, second)
            }
        }
    }
    results.forEach { result ->
        val msg = result.fold(
            { "Odd number $it" },
            { "Even numbers ${it.first} and ${it.second}" }
        )
        println(msg)
    }
}
