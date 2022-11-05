package solution

import java.util.ArrayList
import java.util.Date

object Utilities {
    val specialOffers: List<Flight>
        get() {
            val results = ArrayList<Flight>()
            results.add(Flight("Dublin", "New York", Date()))
            results.add(Flight("Dublin", "Paris", Date()))
            results.add(Flight("Dublin", "Barcelona", Date()))
            return results
        }
    val airports: List<String>
        get() {
            val results = ArrayList<String>()
            results.add("Heathrow")
            results.add("Belfast International")
            results.add("Belfast City")
            results.add("Dublin")
            results.add("Birmingham")
            results.add("Luton")
            return results
        }
    val months: Array<String>
        get() = arrayOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
    val days: Array<Int>
        get() = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
}
