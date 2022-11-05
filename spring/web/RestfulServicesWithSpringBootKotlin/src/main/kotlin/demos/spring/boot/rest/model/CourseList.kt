package demos.spring.boot.rest.model

import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "listOfCourses")
class CourseList(var courses: Collection<Course>?) {
    constructor(): this(null)
}
