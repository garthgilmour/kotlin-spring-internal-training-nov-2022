package videostore

class Customer(val name: String) {
    private val rentals = mutableListOf<Rental>()

    fun addRental(arg: Rental) = rentals.add(arg)

    fun statement(): String {
        fun totalCost() = rentals.sumByDouble { it.cost() }
        fun totalPoints() = rentals.sumBy { it.points() }
        fun rentalLine(r: Rental) = "\t${r.daysRented}\t${r.movie.title}\t${r.cost()}\n"
        fun rentalLines() = rentals.joinToString(separator = "") { rentalLine(it) }

        fun header() = "Rental Record for $name\n"
        fun footer() = """
                   Amount owed is ${totalCost()}
                   You earned ${totalPoints()} frequent renter points
                """.trimIndent()

        return header() + rentalLines() + footer()
    }
}
