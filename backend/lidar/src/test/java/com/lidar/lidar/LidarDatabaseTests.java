package com.lidar.lidar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class LidarDatabaseTests {

	@Autowired
    private MockMvc mvc;

	@Test
	public void testDatabase() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/test/database/create?name=aaa").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
				.andExpect(content().string(matchesPattern("[0-9]*, aaa")));
		mvc.perform(MockMvcRequestBuilders.get("/test/database/read").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
				.andExpect(content().string(matchesPattern("1")));
		mvc.perform(MockMvcRequestBuilders.post("/test/database/delete").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
				.andExpect(content().string(equalTo("All entries deleted.")));
		mvc.perform(MockMvcRequestBuilders.get("/test/database/read").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
				.andExpect(content().string(equalTo("0")));
	}

}
