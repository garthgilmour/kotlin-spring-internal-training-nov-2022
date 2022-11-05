package demos.spring.boot.rest.logic

import demos.spring.boot.rest.model.Quote

interface QuotesEngine {
    fun generateQuote(courseId: String): Quote?
}
