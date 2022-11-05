package functions.currying.basic

import arrow.core.curried

val addThree = { a:Int, b: Int, c:Int ->
    println("Adding $a, $b and $c")
    a + b + c
}

fun printLine() = println("--------------------")

fun main() {
    println(addThree(10,20,40))
    printLine()

    val f1 = addThree.curried()
    val f2 = f1(10)
    val f3 = f2(20)
    val result = f3(40)
    println(result)
    printLine()

    println(f1(10)(20)(40))
    printLine()
}
