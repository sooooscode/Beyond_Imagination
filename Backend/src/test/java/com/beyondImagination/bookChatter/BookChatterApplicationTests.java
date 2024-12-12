package com.beyondImagination.bookChatter;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
class BookChatterApplicationTests {

	@Autowired
	private MockMvc mockMvc; // Inject MockMvc to perform HTTP requests

	@Test
	void contextLoads() throws Exception {

		log.info("Starting contextLoads test"); // 로그 출력

		// Perform a GET request and check if the response status is OK
		mockMvc.perform(get("/"))
				.andExpect(status().isOk());

		log.info("Finished contextLoads test"); // 로그 출력
	}
}
