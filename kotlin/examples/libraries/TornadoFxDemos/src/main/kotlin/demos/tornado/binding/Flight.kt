package demos.tornado.binding

import tornadofx.getProperty
import tornadofx.property
import java.time.LocalDate

fun sampleFlights(): List<Flight> {
    return listOf(
        Flight.of("LHR","ORD",1,2),
        Flight.of("DUB","JFK",3,4),
        Flight.of("EDI","CDG",5,6),
        Flight.of("LHR","ORD",7,8),
        Flight.of("DUB","JFK",9,10),
        Flight.of("EDI","CDG",11,12),
        Flight.of("LHR","ORD",13,14),
        Flight.of("DUB","JFK",15,16),
        Flight.of("EDI","CDG",17,18),
        Flight.of("LHR","ORD",19,20))
}

class Flight(origin: String,
             destination: String,
             val departure: LocalDate,
             val arrival: LocalDate) {

    var origin by property(origin)
    fun originProperty() = getProperty(Flight::origin)

    var destination by property(destination)
    fun destinationProperty() = getProperty(Flight::destination)

    companion object {
        fun of(origin: String, destination: String, depDay: Int, arrDay: Int): Flight {
            val date1 = LocalDate.now().withDayOfMonth(depDay)
            val date2 = LocalDate.now().withDayOfMonth(arrDay)
            return Flight(origin, destination, date1, date2)
        }
    }
}