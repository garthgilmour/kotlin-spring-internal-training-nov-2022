package demos.spring.boot.rest.model

class Course(var id: String, var title: String, var difficulty: CourseDifficulty, var duration: Int) {
    constructor() : this("", "", CourseDifficulty.BEGINNER, 0)
}

