package reactor.switching

import reactor.core.publisher.Flux
import java.time.Duration
import java.time.LocalTime
import java.time.format.DateTimeFormatter

val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss")

fun time(): String = LocalTime.now().format(formatter)

fun buildBaseData(): Flux<String> = Flux
    .just("Alpha", "Bravo", "Charlie")
    .delayElements(Duration.ofSeconds(2))

fun buildTestData(prefix: String): Flux<String> {
    val testData = Flux.generate<String?, Int?>({ -1 }) { state, sink ->
        val nextState = state + 1
        when (nextState) {
            21 -> sink.complete()
            else -> sink.next("$prefix: $nextState at ${time()}")
        }
        nextState
    }
    return testData.delayElements(Duration.ofMillis(250))
}

fun main() {
    buildBaseData()
        .switchMap(::buildTestData)
        .subscribe(
            { item -> println("\t$item") },
            { error -> println("Error: ${error.message}") },
            { println("Stream complete") }
        )

    println("Hit return to end")
    readln()
    println("Bye")
}
