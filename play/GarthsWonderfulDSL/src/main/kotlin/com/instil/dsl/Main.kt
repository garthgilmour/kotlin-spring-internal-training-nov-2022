package com.instil.dsl

class Section(index: Int)

data class Module(val title: String) {
    private val sections = mutableListOf<Section>()

    fun section (
        index: Int = 0,
        action: Section.() -> Unit
    ) = Section(index).apply(action).also { sections.add(it) }
}

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
            section {

            }
        }
        module {
            section {

            }
        }
    }

    val dsl2 = course("Intro To Kotlin") {
        module("Getting Started") {
            section(0) {

            }
            section(1) {

            }
        }
        module("Procedural Programming") {
            section(0) {

            }
        }
        module("Object Oriented Programming") {
            section(0) {

            }
            section(1) {

            }
            section(2) {

            }
        }
    }

    println(dsl1)
    println(dsl2)
}
