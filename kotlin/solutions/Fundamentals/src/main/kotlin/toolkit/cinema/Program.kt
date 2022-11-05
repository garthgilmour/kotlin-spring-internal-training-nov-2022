package toolkit.cinema

fun main() {
    val movies = greatActionMovies()
    allTheMovieTitles(movies)
    allTheGreatMovieTitles(movies)
    titleAndRatingOfMoviesFrom1984(movies)
    allTheQuotes(movies)
    averageQuoteLength(movies)
    moviesListedByDecade(movies)
    moviesGroupedByRating(movies)
}

fun allTheMovieTitles(movies: List<Movie>) {
    println("The titles of all the movies")
    movies.map { m -> m.title }.forEach(::printTabbed)
}

fun allTheGreatMovieTitles(movies: List<Movie>) {
    println("Titles of all the movies with a rating of GREAT")
    movies.filter { m -> m.rating == Rating.GREAT }
            .map { m -> m.title }
            .forEach(::printTabbed)
}

fun titleAndRatingOfMoviesFrom1984(movies: List<Movie>) {
    println("Title and rating of movies released in 1984")
    movies.filter { m -> m.releaseDate.year == 1984 }
            .map { m -> Pair(m.title, m.rating) }
            .forEach { p -> println("\t${ p.first } rated ${ p.second }") }
}

fun allTheQuotes(movies: List<Movie>) {
    println("All the quotes")
    movies.flatMap { m -> m.quotes }
            .forEach(::printTabbed)
}

fun averageQuoteLength(movies: List<Movie>) {
    println("The average length of a quote is")
    val result = movies.flatMap { m -> m.quotes }
                        .map { m -> m.length }
                        .average()
    System.out.printf("\t%.2f\n", result)
}

fun moviesListedByDecade(movies: List<Movie>) {
    val (oldMovies, newMovies) = movies.partition { m -> m.releaseDate.year < 1990 }

    println("The titles of all the movies from the 1980s")
    oldMovies.map { m -> m.title }.forEach(::printTabbed)

    println("The titles of all the movies from the 1990s")
    newMovies.map { m -> m.title }.forEach(::printTabbed)
}

fun moviesGroupedByRating(movies: List<Movie>) {
    println("The movies grouped by their rating:")
    movies.groupBy { m -> m.rating }
            .entries
            .forEach { entry ->
                println("\tMovies with a rating of ${entry.key}")
                entry.value.map { m -> m.title }
                        .forEach { t -> println("\t\t$t") }
            }
}

fun printTabbed(input: String) {
    println("\t$input")
}
