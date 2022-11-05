package videostore

class Rental(val movie: Movie, val daysRented: Int) {
    fun cost() = movie.cost(daysRented)
    fun points() = movie.points(daysRented)
}
