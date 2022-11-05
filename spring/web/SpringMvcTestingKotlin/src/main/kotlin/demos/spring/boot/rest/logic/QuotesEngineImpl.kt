package demos.spring.boot.rest.logic

import demos.spring.boot.rest.model.Course
import demos.spring.boot.rest.model.CourseDifficulty.*
import demos.spring.boot.rest.model.Quote
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class QuotesEngineImpl(@Qualifier("portfolio") val portfolio: MutableMap<String, Course>) : QuotesEngine {
    override fun generateQuote(courseId: String): Quote? {
        val course = portfolio[courseId]
        return if(course != null) buildQuote(course) else null
    }

    private fun buildQuote(course: Course): Quote {
        val rate = when (course.difficulty) {
            BEGINNER -> 1200.0
            INTERMEDIATE -> 1400.0
            ADVANCED -> 1600.0
        }
        return Quote(course, course.duration * rate)
    }

}
