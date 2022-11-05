package demos.kotlin.coroutines.flows

import demos.kotlin.coroutines.shared.timeAndThread
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.*

fun output(data: String) = println(timeAndThread(data))

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun genFlow(characters: List<String>): Flow<String> = flow {
    coroutineScope {
        val channel = produce(capacity = characters.size) {
            for (character in characters) {
                delay(1000)
                output("Generated $character")
                send(character)
            }
        }
        channel.consumeEach {
            emit(it)
        }
    }
}.flowOn(Dispatchers.IO)

fun main() = runBlocking {
    val flintstones = listOf("Fred", "Wilma", "Barney", "Betty", "Pebbles")
    val simpsons = listOf("Homer", "Marge", "Bart", "Liza", "Maggie")

    genFlow(flintstones).collect { output("Processed $it") }
    genFlow(simpsons).collect { output("Processed $it") }
}
