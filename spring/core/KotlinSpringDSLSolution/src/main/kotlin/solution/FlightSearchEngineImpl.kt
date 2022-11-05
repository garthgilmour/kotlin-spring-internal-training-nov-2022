package solution

import org.springframework.stereotype.Component
import java.util.*

@Component
class FlightSearchEngineImpl: FlightSearchEngine {
    override fun findFlights(origin: String, destination: String, departure: Date): List<Flight> {
        val results = mutableListOf<Flight>()

        val calendar = GregorianCalendar()
        calendar.time = departure
        calendar.set(Calendar.AM_PM, Calendar.PM)
        calendar.set(Calendar.HOUR, 2)

        for (i in 0..4) {
            calendar.roll(Calendar.HOUR, 1)
            results.add(Flight(origin, destination, calendar.time))
        }
        return results
    }
}
