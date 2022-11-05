package solution

interface FlightBookingService {
    fun makeBooking(selected: Flight): Boolean
}
