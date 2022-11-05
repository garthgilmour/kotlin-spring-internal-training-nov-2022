package demos.spring.boot.rest.extended

import demos.spring.boot.rest.DeletionException
import demos.spring.boot.rest.model.Course
import demos.spring.boot.rest.model.CourseList
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController("extendedCoursesController")
@RequestMapping("/extended/courses")
class CoursesController(@Qualifier("portfolio") val portfolio: MutableMap<String, Course>) {

    @RequestMapping(method = [RequestMethod.GET], produces = ["application/xml"])
    fun viewAllAsXml(): ResponseEntity<CourseList> {
        return if (portfolio.isEmpty()) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(CourseList(portfolio.values), HttpStatus.OK)
        }
    }

    @RequestMapping(method = [RequestMethod.GET], produces = ["application/json"])
    fun viewAllAsJson(): ResponseEntity<Collection<Course>> {
        return if (portfolio.isEmpty()) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            val headers = HttpHeaders()
            headers["NumCourses"] = portfolio.values.size.toString()
            ResponseEntity(portfolio.values, headers, HttpStatus.OK)
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = ["/{id}"], method = [RequestMethod.PUT], consumes = ["application/json"])
    fun addOrUpdateCourseViaJson(@RequestBody newCourse: Course) {
        portfolio[newCourse.id] = newCourse
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = ["/{id}"], method = [RequestMethod.PUT], consumes = ["application/xml"])
    fun addOrUpdateCourseViaXml(@RequestBody newCourse: Course) {
        portfolio[newCourse.id] = newCourse
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET], produces = ["application/json"])
    fun fetchCourseDetailsAsJson(@PathVariable("id") id: String): ResponseEntity<Course> {
        return if (portfolio.containsKey(id)) {
            ResponseEntity(portfolio[id], HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET], produces = ["application/xml"])
    fun fetchCourseDetailsAsXml(@PathVariable("id") id: String): ResponseEntity<Course> {
        return if (portfolio.containsKey(id)) {
            ResponseEntity(portfolio[id], HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.DELETE])
    fun deleteACourse(@PathVariable("id") id: String): ResponseEntity<String> {
        return if (portfolio.containsKey(id)) {
            if (portfolio[id]!!.title.contains("Scala")) {
                throw DeletionException("Cannot remove Scala courses!")
            }
            portfolio.remove(id)
            ResponseEntity("[\"Removed $id\"]", HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}
