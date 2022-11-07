package com.instil.dsl

interface Node {

}

abstract class ContainerNode<T> : Node {
    private val children = mutableListOf<T>()

    protected fun configureAndAddChild(
        child: T,
        action: T.() -> Unit
    ) = child.apply(action).also { children.add(it) }

    protected fun addChild(child: T) = children.add(child)
}

class Topic(val content: String) : Node

class Section(index: Int) : ContainerNode<Topic>() {
    operator fun String.unaryPlus() = addChild(Topic(this))
}

class Module(val title: String) : ContainerNode<Section>() {

    fun section(
        index: Int = 0,
        action: Section.() -> Unit
    ) = configureAndAddChild(Section(index), action)
}

class Course(private val title: String) : ContainerNode<Module>() {

    fun module(
        title: String = "default module title",
        action: Module.() -> Unit
    ) = configureAndAddChild(Module(title), action)

    override fun toString() = "Course with title '$title'"
}

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
