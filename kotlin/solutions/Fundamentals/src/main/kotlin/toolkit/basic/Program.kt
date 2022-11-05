package toolkit.basic

import java.lang.Math.random
import java.text.DecimalFormat

fun main() {
    val formatter = DecimalFormat(".00")
    val input = (1..10).map { (1..10).map { random() } }

    solution1(input, formatter)
    solution2(input, formatter)
    solution3(input, formatter)
}

private fun solution1(input: List<List<Double>>, formatter: DecimalFormat) {
    println("Solution no 1")
    input.flatMap { it }
            .filter { num -> num > 0.5 }
            .map { num -> formatter.format(num) }
            .forEach(::println)
}

private fun solution2(input: List<List<Double>>, formatter: DecimalFormat) {
    println("Solution no 2")
    input.flatMap { it.filter { num -> num > 0.5 } }
            .map { num -> formatter.format(num) }
            .forEach(::println)
}

private fun solution3(input: List<List<Double>>, formatter: DecimalFormat) {
    println("Solution no 3")
    input.flatMap { it.filter { num -> num > 0.5 }
                        .map { num -> formatter.format(num) } }
            .forEach(::println)
}
