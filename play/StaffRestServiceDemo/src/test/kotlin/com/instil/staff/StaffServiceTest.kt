package com.instil.staff

import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class StaffServiceTest {
	companion object {
		private val JSON_CONTENT_TYPE = MediaType.parseMediaType("application/json;charset=UTF-8")
	}

	@Autowired
	private lateinit var mockMvc: MockMvc

	@Test
	fun allStaffCanBeFound() {
		mockMvc.perform(get("/staff").accept(JSON_CONTENT_TYPE))
			.andExpect(jsonPath("$").isArray)
			.andExpect(jsonPath("$", hasSize<Any>(6)))
			.andExpect(jsonPath("$[?(@.age == 30)]", hasSize<Any>(3)))
			.andExpect(jsonPath("$[?(@.department == 'Sales')]", hasSize<Any>(2)))
			.andExpect { result ->
				val responseBody = result.response.contentAsString
				assertTrue(
					responseBody.contains("Jane"),
					"Sample name not found"
				)
			}
	}

}
