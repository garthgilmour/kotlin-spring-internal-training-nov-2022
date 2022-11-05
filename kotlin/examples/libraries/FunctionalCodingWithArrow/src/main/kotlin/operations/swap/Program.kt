package operations.swap

import arrow.core.Either.*

fun main() {
    val func = { num: Int -> if(num % 2 == 0) Right(num) else Left(num) }
    val data = (1..6).map(func)

    val result = data.map { either ->
        either.swap()
    }
    println(data)
    println(result)
}