package demos.tornado.forms

import javafx.collections.FXCollections.observableArrayList
import javafx.scene.control.ToggleGroup
import tornadofx.*

class FormLayoutView: View("Using the Form Layout") {
    private val genres = observableArrayList("Action", "Comedy", "Thriller", "Romance")

    override val root = form {
        fieldset("Personal Details") {
            field("Forename") {
                textfield()
            }
            field("Surname") {
                textfield()
            }
            field("DOB") {
                datepicker()
            }
            field("Occupation") {
                val tg = ToggleGroup()
                hbox(5) {
                    radiobutton("Astronaut", tg) {
                        isSelected = true
                    }
                    radiobutton("Brain Surgeon", tg)
                    radiobutton("Marine Biologist", tg)
                    radiobutton("Ninja", tg)
                }
            }
        }
        fieldset("Preferences") {
            field("Movies") {
                combobox(values = genres)
            }
            field("Retro TV") {
                checkbox("Blakes 7")
                checkbox("Buck Rogers")
                checkbox("Flash Gordon")
                checkbox("Space 1999")
            }
            field("Pineapple on Pizza?") {
                togglebutton("") {
                    val stateText = selectedProperty().stringBinding {
                        if (it == true) "Of Course!" else "Hell No!"
                    }
                    textProperty().bind(stateText)
                }
            }

        }
    }
}

class FormLayoutApp: App(FormLayoutView::class)

fun main(args: Array<String>) {
    launch<FormLayoutApp>(args)
}
