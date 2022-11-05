package dsl

fun main() {
    val page = html {
        body {
            h1 {
                style("{ font-weight: bold }")
                hidden(false)

                +"This is an example of a DSL"
            }
            div {
                p {
                    hidden(true)
                    +"This is where we could add some content."
                    +"Maybe some more"
                }
            }
        }
    }

    println(page.dump())
}

abstract class HtmlNode(val name: String) {
    val children = mutableListOf<HtmlNode>()
    val attrs = mutableListOf<Pair<String, String>>()

    open fun dump(prefix: String = ""): String {
        fun Pair<String, String>.asAttribute() = "$first=\"$second\""

        val dumpedChildren = children.joinToString("") { it.dump("$prefix    ") }
        val attrs = if (attrs.any())
            attrs.joinToString(prefix = " ", separator = " ") { it.asAttribute() }
        else
            ""

        return "$prefix<$name$attrs>\n$dumpedChildren$prefix</$name>\n"
    }

    fun createChild(name: String, builder: BodyElement.() -> Unit): BodyElement {
        return BodyElement(name)
            .apply(builder)
            .also { children.add(it) }
    }
}

class StringNode(private val content: String) : HtmlNode("") {
    override fun dump(prefix: String): String {
        return prefix + content + "\n"
    }
}

class Html : HtmlNode("html") {
    fun body(builder: BodyElement.() -> Unit) = createChild("body", builder)
}

class BodyElement(name: String) : HtmlNode(name) {

    fun div(builder: BodyElement.() -> Unit): BodyElement {
        return createChild("div", builder)
    }

    fun p(builder: BodyElement.() -> Unit): BodyElement {
        return createChild("p", builder)
    }

    fun h1(builder: BodyElement.() -> Unit): BodyElement {
        return createChild("h1", builder)
    }

    operator fun String.unaryPlus(): Unit {
        children.add(StringNode(this))
    }

    fun style(content: String): Unit {
        attrs.add(Pair("style", content))
    }

    fun hidden(hidden: Boolean): Unit {
        attrs.add(Pair("hidden", hidden.toString()))
    }
}

fun html(builder: Html.() -> Unit): Html {
    return Html().apply(builder)
}
