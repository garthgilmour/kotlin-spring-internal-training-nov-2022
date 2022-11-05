package operations.interleave

import arrow.core.interleave

fun main() {
    val data1 = listOf(1, 2, 3, 4, 5)
    val data2 = listOf(0.1, 0.2, 0.3, 0.4, 0.5)

    val result = data1.interleave(data2)
    println(result)
}
