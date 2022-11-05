package solution

import java.util.*

interface FlightSearchEngine {
    fun findFlights(origin: String, destination: String, departure: Date): List<Flight>
}
