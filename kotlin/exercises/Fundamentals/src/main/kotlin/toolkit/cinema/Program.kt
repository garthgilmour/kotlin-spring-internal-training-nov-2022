package cinema.start

import toolkit.cinema.Movie
import toolkit.cinema.greatActionMovies

fun main() {
    val movies = greatActionMovies()
    allTheMovieTitles(movies)
    allTheGreatMovieTitles(movies )
    titleAndRatingOfMoviesFrom1984(movies)
    allTheQuotes(movies)
    averageQuoteLength(movies)
    moviesListedByDecade(movies)
    moviesGroupedByRating(movies)
}

fun allTheMovieTitles(movies: List<Movie>) {
    println("The titles of all the movies")
}

fun allTheGreatMovieTitles(movies: List<Movie>) {
    println("Titles of all the movies with a rating of GREAT")
}

fun titleAndRatingOfMoviesFrom1984(movies: List<Movie>) {
    println("Title and rating of movies released in 1984")
}

fun allTheQuotes(movies: List<Movie>) {
    println("All the quotes")
}

fun averageQuoteLength(movies: List<Movie>) {
    println("The average length of a quote is")
}

fun moviesListedByDecade(movies: List<Movie>) {
    println("The titles of all the movies from the 1980s")
    println("The titles of all the movies from the 1990s")
}

fun moviesGroupedByRating(movies: List<Movie>) {
    println("The movies grouped by their rating:")
}

fun printTabbed(input: String) {
    println("\t$input")
}
