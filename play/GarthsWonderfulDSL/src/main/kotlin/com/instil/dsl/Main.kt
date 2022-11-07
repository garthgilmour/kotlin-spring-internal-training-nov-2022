package com.instil.dsl

data class Course(val title: String)

fun course(title: String = "default title", action: Course.() -> Unit): Course {
    val course = Course(title)
    course.apply(action)
    return course
}

fun main(args: Array<String>) {
    println("Examples of Kotlin DSLs")

    val dsl1 = course {
        println("In the training course called $title")
    }

    val dsl2 = course("Intro To Kotlin") {
        println("In the training course called $title")
    }

    println(dsl1)
    println(dsl2)
}
