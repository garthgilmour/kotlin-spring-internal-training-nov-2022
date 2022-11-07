package com.instil.dsl

data class Module(val title: String)
data class Course(val title: String) {
    private val modules = mutableListOf<Module>()

    fun module(
        title: String = "default module title",
        action: Module.() -> Unit
    ) = Module(title).apply(action).also { modules.add(it) }

    override fun toString() = "Course with title '$title' and ${modules.size} modules"
}

// OLD VERSION - KEPT FOR ILLUSTRATION
//fun course(title: String = "default title", action: Course.() -> Unit): Course {
//    val course = Course(title)
//    course.apply(action)
//    return course
//}

fun course(
    title: String = "default course title",
    action: Course.() -> Unit
) = Course(title).apply(action)

fun main(args: Array<String>) {
    println("Examples of Kotlin DSLs")

    val dsl1 = course {
        module {

        }
        module {

        }
    }

    val dsl2 = course("Intro To Kotlin") {
        module("Getting Started") {

        }
        module("Procedural Programming") {

        }
        module("Object Oriented Programming") {

        }
    }

    println(dsl1)
    println(dsl2)
}
