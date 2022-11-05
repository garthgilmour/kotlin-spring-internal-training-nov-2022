package demos.spring.boot.rest.services

import demos.spring.boot.rest.model.Course
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/courses")
class CoursesController(@Qualifier("portfolio") val portfolio: MutableMap<String, Course>) {

    @GetMapping(produces = ["application/json"])
    fun viewAllAsJson() = portfolio.values

    @PutMapping(value = ["/{id}"], consumes = ["application/json"])
    fun addOrUpdateCourseViaJson(@RequestBody newCourse: Course): Course {
        portfolio[newCourse.id] = newCourse
        return newCourse
    }

    @GetMapping(value = ["/{id}"], produces = ["application/json"])
    fun fetchCourseDetailsAsJson(@PathVariable("id") id: String) = portfolio[id]

    @DeleteMapping(value = ["/{id}"])
    fun deleteACourse(@PathVariable("id") id: String) = portfolio.remove(id)
}
