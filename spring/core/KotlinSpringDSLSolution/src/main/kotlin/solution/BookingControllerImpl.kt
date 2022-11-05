package solution

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class BookingControllerImpl @Autowired constructor(
        private val searchEngine: FlightSearchEngine,
        private val bookingService: FlightBookingService) : BookingController {

    override fun findAvailableFlights(origin: String, destination: String, departure: Date): List<Flight> {
        return searchEngine.findFlights(origin, destination, departure)
    }

    override fun bookFlight(selected: Flight): Boolean {
        return bookingService.makeBooking(selected)
    }
}
