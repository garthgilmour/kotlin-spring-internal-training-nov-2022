package demos.tornado.hello

import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class HelloWorldView: View("My TornadoFx UI") {
    private val displayText = SimpleStringProperty("Default Text")

    override val root = hbox(20) {
        button("Press me") {
            action {
                println("Button Pushed")
                displayText.value = "ButtonPushed"
            }
        }
        label(displayText)
    }
}

class HelloWorldApp: App(HelloWorldView::class)

fun main(args: Array<String>) {
    launch<HelloWorldApp>(args)
}
