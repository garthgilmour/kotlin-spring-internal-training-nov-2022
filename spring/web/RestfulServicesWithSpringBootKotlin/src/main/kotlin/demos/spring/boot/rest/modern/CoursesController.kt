package demos.spring.boot.rest.modern

import demos.spring.boot.rest.DeletionException
import demos.spring.boot.rest.model.Course
import demos.spring.boot.rest.model.CourseDifficulty
import demos.spring.boot.rest.model.CourseList
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.collections.HashSet

@RestController("modernCoursesController")
@RequestMapping("/modern/courses")
class CoursesController(@Qualifier("portfolio") val portfolio: MutableMap<String, Course>) {

    @GetMapping(produces = ["application/xml"])
    fun viewAllAsXml(@RequestParam("type") type: Optional<CourseDifficulty>): ResponseEntity<CourseList> {
        return if (portfolio.isEmpty()) {
            notFound().build()
        } else {
            val courses: MutableCollection<Course> = HashSet(portfolio!!.values)
            type.ifPresent { t: CourseDifficulty -> courses.removeIf { c: Course -> c.difficulty != t } }
            ok(CourseList(courses))
        }
    }

    @GetMapping(produces = ["application/json"])
    fun viewAllAsJson(@RequestParam("type") type: Optional<CourseDifficulty>): ResponseEntity<Collection<Course>> {
        return if (portfolio.isEmpty()) {
            notFound().build()
        } else {
            val courses: MutableCollection<Course> = HashSet(portfolio.values)
            type.ifPresent { t: CourseDifficulty -> courses.removeIf { c: Course -> c.difficulty != t } }
            val headers = HttpHeaders()
            headers["NumCourses"] = courses.size.toString()
            ResponseEntity(courses, headers, HttpStatus.OK)
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = ["/{id}"], consumes = ["application/json"])
    fun addOrUpdateCourseViaJson(@RequestBody newCourse: Course) {
        portfolio[newCourse.id] = newCourse
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = ["/{id}"], consumes = ["application/xml"])
    fun addOrUpdateCourseViaXml(@RequestBody newCourse: Course) {
        portfolio[newCourse.id] = newCourse
    }

    @GetMapping(value = ["/{id}"], produces = ["application/json"])
    fun fetchCourseDetailsAsJson(@PathVariable("id") id: String): ResponseEntity<Course> {
        return if (portfolio.containsKey(id)) {
            ok(portfolio[id])
        } else {
            notFound().build()
        }
    }

    @GetMapping(value = ["/{id}"], produces = ["application/xml"])
    fun fetchCourseDetailsAsXml(@PathVariable("id") id: String): ResponseEntity<Course> {
        return if (portfolio.containsKey(id)) {
            ok(portfolio[id])
        } else {
            notFound().build()
        }
    }

    @DeleteMapping(value = ["/{id}"])
    fun deleteACourse(@PathVariable("id") id: String): ResponseEntity<String> {
        return if (portfolio.containsKey(id)) {
            if (portfolio[id]!!.title.contains("Scala")) {
                throw DeletionException("Cannot remove Scala courses!")
            }
            portfolio.remove(id)
            ok("[\"Removed $id\"]")
        } else {
            notFound().build()
        }
    }
}
