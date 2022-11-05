package solution

import java.util.*

interface BookingController {
    fun findAvailableFlights(origin: String, destination: String, departure: Date): List<Flight>
    fun bookFlight(selected: Flight): Boolean
}
