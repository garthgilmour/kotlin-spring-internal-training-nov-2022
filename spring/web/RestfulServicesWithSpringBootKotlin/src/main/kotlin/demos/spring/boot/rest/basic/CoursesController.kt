package demos.spring.boot.rest.basic

import demos.spring.boot.rest.model.Course
import demos.spring.boot.rest.model.CourseList
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.*

@RestController("basicCoursesController")
@RequestMapping("/basic/courses")
class CoursesController(@Qualifier("portfolio") val portfolio: MutableMap<String, Course>) {

    @RequestMapping(method = [RequestMethod.GET], produces = ["application/xml"])
    fun viewAllAsXml(): CourseList {
        return CourseList(portfolio.values)
    }

    @RequestMapping(method = [RequestMethod.GET], produces = ["application/json"])
    fun viewAllAsJson(): Collection<Course> {
        return portfolio.values
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.PUT], consumes = ["application/json"])
    fun addOrUpdateCourseViaJson(@RequestBody newCourse: Course): Course {
        portfolio[newCourse.id] = newCourse
        return newCourse
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.PUT], consumes = ["application/xml"])
    fun addOrUpdateCourseViaXml(@RequestBody newCourse: Course): String {
        portfolio[newCourse.id] = newCourse
        return newCourse.id
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET], produces = ["application/json"])
    fun fetchCourseDetailsAsJson(@PathVariable("id") id: String): Course? {
        return portfolio[id]
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET], produces = ["application/xml"])
    fun fetchCourseDetailsAsXml(@PathVariable("id") id: String): Course? {
        return portfolio[id]
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.DELETE])
    fun deleteACourse(@PathVariable("id") id: String) {
        portfolio.remove(id)
    }
}
