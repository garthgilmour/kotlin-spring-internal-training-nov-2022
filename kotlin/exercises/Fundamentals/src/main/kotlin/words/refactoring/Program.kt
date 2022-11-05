package words.refactoring

import java.io.File

fun main() {
    File("./input/uniqueWords.txt").useLines { lines ->
        val tabularResults = lines
            .flatMap { it.split("\\s+".toRegex()) }
            .map { str -> str.lowercase() }
            .map { str -> str.replace("\\W".toRegex(), "") }
            .filter { str: String -> !str.matches("^[0-9]+$".toRegex()) }
            .groupBy { it }

        val listResults = mutableListOf<Pair<String, Int>>()
        tabularResults.forEach { (word, instances) ->
            if (instances.size > 1) {
                listResults.add(Pair(word, instances.size))
            }
        }
        listResults.sortBy { p -> p.second }
        listResults.reverse()
        listResults
            .map { pair -> "${pair.first} ${pair.second}" }
            .forEach(::println)
    }
}
