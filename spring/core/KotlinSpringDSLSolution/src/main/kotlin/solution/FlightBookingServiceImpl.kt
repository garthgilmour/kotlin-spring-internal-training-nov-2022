package solution

import org.springframework.stereotype.Component

@Component
class FlightBookingServiceImpl: FlightBookingService {
    override fun makeBooking(selected: Flight) = true
}
