package com.instil

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*

val names = mapOf(
    "Jane" to "Dave",
    "Dave" to "Mary",
    "Mary" to "Pete",
    "Pete" to "Lucy",
    "Lucy" to "Waldo"
)

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    routing {
        pingRoute()
        waldoRoute()
    }
}

fun Route.pingRoute() {
    get("/ping/{timeout}") {
        val timeout = call.parameters["timeout"]?.toInt() ?: 0

        if (timeout <= 10) {
            // Deliberately blocking current thread
            Thread.sleep((timeout * 1000).toLong())

            val successMsg = "[\"Pingback after $timeout\"]"
            call.respondText(successMsg, contentType = ContentType.Application.Json)
        } else {
            val errorMsg = "[\"Errorback - timeout of $timeout is too high!\"]"
            call.respondText(
                errorMsg,
                contentType = ContentType.Application.Json,
                status = HttpStatusCode.InternalServerError
            )
        }
    }
}

fun Route.waldoRoute() {
    get("/wheresWaldo/{name}") {
        val name = call.parameters["name"] ?: ""

        val nextName = names[name]
        if (nextName != null) {
            val successMsg = "[\"$nextName\"]"
            call.respondText(successMsg, contentType = ContentType.Application.Json)
        } else {
            val errorMsg = "[\"No corresponding name for $name\"]"
            call.respondText(
                errorMsg,
                contentType = ContentType.Application.Json,
                status = HttpStatusCode.NotFound
            )
        }
    }
}

