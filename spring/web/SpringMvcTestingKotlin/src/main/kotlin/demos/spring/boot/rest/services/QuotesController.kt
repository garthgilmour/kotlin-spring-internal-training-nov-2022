package demos.spring.boot.rest.services

import demos.spring.boot.rest.logic.QuotesEngine
import demos.spring.boot.rest.model.Quote
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/quotes")
class QuotesController(@Autowired val engine: QuotesEngine) {

    @GetMapping(value = ["{courseId}"], produces = ["application/json"])
    fun fetchQuoteAsJson(@PathVariable courseId: String): ResponseEntity<Quote> {
        val quote = engine.generateQuote(courseId)
        return if(quote != null) ok(quote) else notFound().build()
    }
}
