package toolkit.cinema

import java.time.LocalDate

class Movie(val title: String,
            val rating: Rating,
            val releaseDate: LocalDate,
            val quotes: List<String>)
