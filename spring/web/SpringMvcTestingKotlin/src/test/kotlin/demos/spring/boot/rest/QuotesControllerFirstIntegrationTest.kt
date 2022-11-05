package demos.spring.boot.rest

import demos.spring.boot.rest.logic.QuotesEngine
import demos.spring.boot.rest.model.Course
import demos.spring.boot.rest.model.Quote
import demos.spring.boot.rest.services.QuotesController
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(QuotesController::class)
class QuotesControllerFirstIntegrationTest {
    companion object {
        private val JSON_CONTENT_TYPE = MediaType.parseMediaType("application/json;charset=UTF-8")
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var engine: QuotesEngine

    @BeforeEach
    fun start() {
        val tmpQuote = Quote(Course(), 5000.0)
        Mockito.`when`(engine.generateQuote(ArgumentMatchers.anyString())).thenReturn(tmpQuote)
    }

    @Test
    fun quotesWork() {
        mockMvc.perform(get("/quotes/AB12").accept(JSON_CONTENT_TYPE))
            .andExpect(jsonPath("$.amount").value(5000.0))
    }
}

