package com.beyondImagination.bookChatter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BookChatterApplicationTests {

	@Autowired
	private MockMvc mockMvc; // Inject MockMvc to perform HTTP requests

	@Test
	void contextLoads() throws Exception {
		// Perform a GET request and check if the response status is OK
		mockMvc.perform(get("/"))
				.andExpect(status().isOk());
	}
}
