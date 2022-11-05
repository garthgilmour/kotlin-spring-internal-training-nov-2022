package com.instil

import com.instil.model.Builder
import com.instil.model.Course
import org.http4k.core.*
import org.http4k.core.Status.Companion.OK
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.routing.*
import org.http4k.server.Netty
import org.http4k.server.asServer
import org.http4k.format.Jackson
import org.http4k.format.Jackson.json

val json = Jackson

fun main() {
    val portfolio = Builder.buildPortfolio()

    val allCourses = { _: Request ->
        Response(OK)
            .header("Content-Type", "application/json")
            .body(json.asJsonString(portfolio.values))
    }

    val singleCourse = { request: Request ->
        val id = request.path("id")
        val course = portfolio[id]
        if(course != null) {
            Response(OK)
                .header("Content-Type", "application/json")
                .body(json.asJsonString(course))
        } else {
            Response(NOT_FOUND)
                .header("Content-Type", "text/plain")
                .body("Course $id not found")
        }
    }

    val deleteCourse =  { request: Request ->
        val id = request.path("id")
        if(portfolio.containsKey(id)) {
            portfolio.remove(id)
            Response(OK)
                .header("Content-Type", "text/plain")
                .body("Course $id removed")
        } else {
            Response(NOT_FOUND)
                .header("Content-Type", "text/plain")
                .body("Course $id not found")
        }
    }

    val addOrUpdateCourse = { request: Request ->
        val id = request.path("id")
        val lens = Body.json().toLens()
        if (id != null) {
            portfolio[id] = json.asA(lens.extract(request), Course::class)
            Response(OK)
                .header("Content-Type", "text/plain")
                .body("Course $id added or updated")
        } else {
            Response(NOT_FOUND)
                .header("Content-Type", "text/plain")
                .body("Course id not specified")
        }
    }

    routes(
        "/courses" bind routes(
            "/" bind Method.GET to allCourses,
            "/{id:.*}" bind routes (
                Method.GET to singleCourse,
                Method.DELETE to deleteCourse,
                Method.PUT to addOrUpdateCourse
            )
        )
    ).asServer(Netty(8080))
        .start()
}