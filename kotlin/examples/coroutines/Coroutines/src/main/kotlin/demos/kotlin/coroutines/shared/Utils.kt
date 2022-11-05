package demos.kotlin.coroutines.shared

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*

import java.time.LocalTime
import java.time.format.DateTimeFormatter

suspend fun fetchNextName(name: String): String {
    val client = HttpClient(CIO) {
        install(JsonFeature)
    }
    client.use {
        return it.get<Array<String>>("http://localhost:8080/wheresWaldo/$name") {
            accept(ContentType.Application.Json)
        }[0]
    }
}

suspend fun pingSlowServer(timeout: Int): String {
    val client = HttpClient(CIO) {
        install(JsonFeature)
    }
    client.use {
        return it.get<Array<String>>("http://localhost:8080/ping/$timeout") {
            accept(ContentType.Application.Json)
        }[0]
    }
}

fun timeNow(): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    return formatter.format(LocalTime.now())
}

fun timeAndThread(item: String) = "$item at ${timeNow()} on ${Thread.currentThread().id}"

fun printInDetail(item: String) = println(timeAndThread(item))
