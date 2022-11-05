package demos.spring.boot.rest

import demos.spring.boot.rest.services.DeliveriesController
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(DeliveriesController::class)
class DeliveriesControllerIntegrationTest {
    companion object {
        private val JSON_CONTENT_TYPE = MediaType.parseMediaType("application/json;charset=UTF-8")
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun allDeliveriesCanBeFound() {
        mockMvc.perform(MockMvcRequestBuilders.get("/deliveries").accept(JSON_CONTENT_TYPE))
            .andExpect(MockMvcResultMatchers.jsonPath("$").isArray)
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize<Any>(0)))
    }
}
