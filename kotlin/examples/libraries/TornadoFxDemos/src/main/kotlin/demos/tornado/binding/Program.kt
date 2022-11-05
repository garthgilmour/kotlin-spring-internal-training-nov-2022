package demos.tornado.binding

import tornadofx.*
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class DataBindingView : View("My TornadoFx UI") {
    private val flights = sampleFlights().asObservable()

    override val root = tableview(flights) {
        val dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)

        column("Origin Airport", Flight::originProperty).makeEditable()
        column("Destination Airport", Flight::destinationProperty).makeEditable()
        readonlyColumn("Departure Date", Flight::departure).cellFormat {
            text = dtf.format(it)
        }
        readonlyColumn("Arrival Date", Flight::arrival).cellFormat {
            text = dtf.format(it)
        }
    }
}

class DataBindingApp : App(DataBindingView::class)

fun main(args: Array<String>) {
    launch<DataBindingApp>(args)
}
