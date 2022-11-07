package com.instil.dsl

interface Node {
    fun marshall(level: Int = 0): String
    fun indent(level: Int) = "  ".repeat(level)
}

abstract class ContentNode(
    private val tagName: String,
    private val content: String
) : Node {
    override fun marshall(level: Int) = "${indent(level)}<$tagName>$content</$tagName>"
}

abstract class ContainerNode<T : Node>(private val tagName: String) : Node {
    private val children = mutableListOf<T>()

    protected fun configureAndAddChild(
        child: T,
        action: T.() -> Unit
    ) = child.apply(action).also { children.add(it) }

    protected fun addChild(child: T) = children.add(child)

    override fun marshall(level: Int) = """
        |${indent(level)}<$tagName>
        |${customContent(level + 1)}
        |${marshallChildren(level + 1)}
        |${indent(level)}</$tagName>
    """.trimMargin("|")

    protected abstract fun customContent(level: Int): String

    private fun marshallChildren(level: Int) = children.joinToString(separator = "\n") { it.marshall(level) }
}

class Topic(content: String) : ContentNode("p", content)

class Section(private val index: Int) : ContainerNode<Topic>("div") {
    operator fun String.unaryPlus() = addChild(Topic(this))
    override fun customContent(level: Int) = "${indent(level)}<h3>Section $index</h3>"
}

class Module(private val title: String) : ContainerNode<Section>("div") {

    fun section(
        index: Int = 0,
        action: Section.() -> Unit
    ) = configureAndAddChild(Section(index), action)

    override fun customContent(level: Int) = "${indent(level)}<h2>Module $title</h2>"
}

class Course(private val title: String) : ContainerNode<Module>("html") {

    fun module(
        title: String = "default module title",
        action: Module.() -> Unit
    ) = configureAndAddChild(Module(title), action)

    override fun toString() = "Course with title '$title'"

    override fun customContent(level: Int) = "${indent(level)}<h1>Course $title</h1>"
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

    println(dsl1.marshall())
    println("----------")
    println(dsl2.marshall())
}
