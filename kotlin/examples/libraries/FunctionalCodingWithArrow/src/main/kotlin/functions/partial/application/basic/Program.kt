package functions.partial.application.basic

import arrow.core.partially1
import arrow.core.partially2

fun main() {
    demo1()
    printLine()
    demo2()
}

fun printLine() = println("-------------")

fun demo1() {
    val addNums = { no1: Int, no2: Int ->
        println("Adding $no1 to $no2")
        no1 + no2
    }
    val addSeven = addNums.partially2(7)
    val result = addSeven(3)
    println(result)
}

fun demo2() {
    val addNums = { no1: Int, no2: Int ->
        println("Adding $no1 to $no2")
        no1 + no2
    }
    val addSeven = addNums.partially2(7)
    val addSevenToThree = addSeven.partially1(3)
    val result = addSevenToThree()
    println(result)
}
