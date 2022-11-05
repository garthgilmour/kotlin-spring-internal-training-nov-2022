package operations.bimap

import arrow.core.Either.*

fun main() {
    val func = { num: Int -> if(num % 2 == 0) Right(num) else Left(num) }
    val data = (1..6).map(func)

    val result = data.map { either ->
        either.bimap({ it * 10}, { it * 100 })
    }
    println(result)
}
