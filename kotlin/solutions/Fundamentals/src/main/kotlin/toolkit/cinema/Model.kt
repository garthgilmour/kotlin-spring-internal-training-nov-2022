package toolkit.cinema

import toolkit.cinema.Rating.GREAT
import toolkit.cinema.Rating.SUPERB
import toolkit.cinema.Rating.BRILLIANT
import toolkit.cinema.Rating.LIFE_CHANGING
import java.time.Month.JUNE
import java.time.Month.JULY
import java.time.Month.OCTOBER
import java.time.LocalDate.of

fun greatActionMovies(): List<Movie> {
    val movies = mutableListOf<Movie>()
    movies.add(
        Movie("Conan",
            GREAT,
            of(1984, JUNE, 29),
            listOf("Enough talk!"))
    )
    movies.add(
        Movie("Terminator",
            GREAT,
            of(1984, OCTOBER, 26),
            listOf("I'll be back."))
    )
    movies.add(
        Movie("Terminator 2",
            BRILLIANT,
            of(1991, JULY, 3),
            listOf("Come with me if you want to live.",
                    "Hasta la vista, baby.",
                    "It's in your nature to destroy yourselves."))
    )
    movies.add(
        Movie("Commando",
            SUPERB,
            of(1984, OCTOBER, 4),
            listOf("I have to remind you Sully, this is my weak arm!",
                    "I eat Green Berets for breakfast. And right now, I'm very hungry!",
                    "Don't disturb my friend, he's dead tired.",
                    "Come on Bennett, let's party!",
                    "Let off some steam, Bennett."))
    )
    movies.add(
        Movie("Predator",
            LIFE_CHANGING,
            of(1987, JUNE, 12),
            listOf("Get to the Chopper!",
                    "Stick around.",
                    "If it bleeds, we can kill it.",
                    "He's using the trees.",
                    "We move, five meter spread, no sound."))
    )
    movies.add(
        Movie("Total Recall",
            GREAT,
            of(1990, JUNE, 1),
            listOf("Get your ass to Mars.",
                    "Relax. You'll live longer.",
                    "If I am not me, then who the hell am I?",
                    "Look who's talking."))
    )
    return movies
}
