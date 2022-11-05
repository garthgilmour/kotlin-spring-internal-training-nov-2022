package demos.spring.boot.rest

import demos.spring.boot.rest.model.Course
import demos.spring.boot.rest.model.CourseDifficulty.*
import demos.spring.boot.rest.services.CoursesController
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CoursesControllerUnitTest {
    private lateinit var controller: CoursesController

    @BeforeEach
    fun start() {
        controller = CoursesController(buildTestPortfolio())
    }

    @Test
    fun allCoursesCanBeFound() {
        val courses = controller.viewAllAsJson()
        assertEquals(3, courses.size)
    }

    @Test
    fun coursesCanBeFoundByID() {
        val course = controller.fetchCourseDetailsAsJson("AB12")
        assertEquals("AB12", course!!.id)
    }

    @Test
    fun coursesCanBeRemoved() {
        controller.deleteACourse("AB12")
        val courses = controller.viewAllAsJson()
        assertEquals(2, courses.size)
        if(courses.find { c: Course -> c.id == "AB12" } != null) {
            fail<Any>("Course not deleted")
        }
    }

    @Test
    fun coursesCanBeAdded() {
        controller.addOrUpdateCourseViaJson(Course("YZ89", "Advanced Scala", ADVANCED, 2))
        val courses = controller.viewAllAsJson()
        assertEquals(4, courses.size)
        val course = courses.find { c: Course -> c.id == "YZ89" }
        if(course != null) {
            assertEquals("Advanced Scala", course.title)
        } else {
            fail<Any>("Course not found")
        }
    }

    private fun buildTestPortfolio() = mutableMapOf(
        "AB12" to Course("AB12", "Intro to Scala", BEGINNER, 4),
        "CD34" to Course("CD34", "Meta-Programming in Ruby", ADVANCED, 2),
        "EF56" to Course("EF56", "Writing MySql Stored Procedures", INTERMEDIATE, 1)
    )
}
