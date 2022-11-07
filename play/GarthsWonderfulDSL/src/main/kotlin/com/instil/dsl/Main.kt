package com.instil.dsl

fun course(title: String = "default title", action: () -> Unit) {
    action()
}

fun main(args: Array<String>) {
    println("Examples of Kotlin DSLs")

    val dsl1 = course {
        println("In a training course with default title")
    }

    val dsl2 = course("Intro To Kotlin") {
        println("In a training course with bespoke title")
    }
}
