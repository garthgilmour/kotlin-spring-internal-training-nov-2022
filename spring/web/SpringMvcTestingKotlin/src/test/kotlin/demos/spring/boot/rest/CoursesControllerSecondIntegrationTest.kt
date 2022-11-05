package demos.spring.boot.rest

import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@SpringBootTest
@AutoConfigureMockMvc
class CoursesControllerSecondIntegrationTest {
    companion object {
        private val JSON_CONTENT_TYPE = MediaType.parseMediaType("application/json;charset=UTF-8")
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun allCoursesCanBeFound() {
        mockMvc.perform(get("/courses").accept(JSON_CONTENT_TYPE))
            .andExpect(jsonPath("$").isArray)
            .andExpect(jsonPath("$", Matchers.hasSize<Any>(12)))
            .andExpect(jsonPath("$[?(@.difficulty=='BEGINNER')]", Matchers.hasSize<Any>(4)))
            .andExpect(jsonPath("$[?(@.difficulty=='INTERMEDIATE')]", Matchers.hasSize<Any>(4)))
            .andExpect(jsonPath("$[?(@.difficulty=='ADVANCED')]", Matchers.hasSize<Any>(4)))
    }

    @Test
    fun coursesCanBeFoundByID() {
        mockMvc.perform(get("/courses/AB12").accept(JSON_CONTENT_TYPE))
            .andExpect(status().isOk)
            .andExpect(content().contentType(JSON_CONTENT_TYPE))
            .andExpect(jsonPath("$.id").value("AB12"))
            .andExpect(jsonPath("$.title").value("Intro to Scala"))
            .andExpect(jsonPath("$.difficulty").value("BEGINNER"))
            .andExpect(jsonPath("$.duration").value(4))
    }

    @Test
    @DirtiesContext
    fun coursesCanBeRemoved() {
        mockMvc.perform(delete("/courses/AB12"))
            .andExpect(status().isOk)
        mockMvc.perform(get("/courses").accept(JSON_CONTENT_TYPE))
            .andExpect(jsonPath("$").isArray)
            .andExpect(jsonPath("$", Matchers.hasSize<Any>(11)))
            .andExpect(jsonPath("$[?(@.title=='Intro to Scala')]", Matchers.hasSize<Any>(0)))
    }

    @Test
    @DirtiesContext
    fun coursesCanBeAdded() {
        val content = "{\"id\":\"YZ98\",\"title\":\"Extra Hard Haskell\",\"difficulty\":\"ADVANCED\",\"duration\":5}"
        mockMvc.perform(put("/courses/YZ98").contentType(JSON_CONTENT_TYPE).content(content))
            .andExpect(status().isOk)
        mockMvc.perform(get("/courses").accept(JSON_CONTENT_TYPE))
            .andExpect(jsonPath("$").isArray)
            .andExpect(jsonPath("$", Matchers.hasSize<Any>(13)))
            .andExpect(jsonPath("$[?(@.title=='Extra Hard Haskell')]", Matchers.hasSize<Any>(1)))
        mockMvc.perform(get("/courses/YZ98").accept(JSON_CONTENT_TYPE))
            .andExpect(status().isOk)
            .andExpect(content().contentType(JSON_CONTENT_TYPE))
            .andExpect(jsonPath("$.id").value("YZ98"))
            .andExpect(jsonPath("$.title").value("Extra Hard Haskell"))
            .andExpect(jsonPath("$.difficulty").value("ADVANCED"))
            .andExpect(jsonPath("$.duration").value(5))
    }
}
