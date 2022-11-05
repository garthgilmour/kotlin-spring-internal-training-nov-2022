package exercise

import java.util.*

class BookingController {
    private val searchEngine = FlightSearchEngine()
    private val bookingService = FlightBookingService()

    fun findAvailableFlights(origin: String, destination: String, departure: Date): List<Flight> {
        return searchEngine.findFlights(origin, destination, departure)
    }

    fun bookFlight(selected: Flight): Boolean {
        return bookingService.makeBooking(selected)
    }
}
