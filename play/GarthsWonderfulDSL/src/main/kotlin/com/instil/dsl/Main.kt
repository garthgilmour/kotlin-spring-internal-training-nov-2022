package com.instil.dsl

class Topic(val content: String)

class Section(index: Int) {
    private val topics = mutableListOf<Topic>()
    operator fun String.unaryPlus() = topics.add(Topic(this))
}

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
                +"foo"
                +"bar"
            }
        }
        module {
            section {
                +"wibble"
                +"wobble"
            }
        }
    }

    val dsl2 = course("Intro To Kotlin") {
        module("Getting Started") {
            section(0) {
                +"installing"
                +"creating projects"
            }
            section(1) {
                +"saving"
                +"running Gradle"
            }
        }
        module("Procedural Programming") {
            section(0) {
                +"a"
                +"b"
                +"c"
            }
        }
        module("Object Oriented Programming") {
            section(0) {
                +"a"
                +"b"
                +"c"
                +"d"
            }
            section(1) {
                +"a"
                +"b"
                +"c"
                +"d"
                +"e"
            }
            section(2) {
                +"a"
                +"b"
                +"c"
                +"d"
                +"e"
                +"f"
            }
        }
    }

    println(dsl1)
    println(dsl2)
}
