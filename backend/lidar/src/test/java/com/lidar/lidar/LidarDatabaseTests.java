package com.lidar.lidar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class LidarDatabaseTests {
	@Autowired
	private MockMvc mvc;

	@Test
	public void testDatabase() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/test/database/create?name=aaa").with(csrf()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
				.andExpect(content().string(equalTo("1, aaa")));
		mvc.perform(MockMvcRequestBuilders.get("/tests/database/read?id=1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

				.andExpect(content().string(matchesPattern("1")));
		mvc.perform(MockMvcRequestBuilders.post("/test/database/delete").with(csrf()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
				.andExpect(content().string(equalTo("Entry not found.")));
		mvc.perform(MockMvcRequestBuilders.get("/tests/database/update?id=1&name=bbb").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
				.andExpect(content().string(equalTo("1, bbb")));
		mvc.perform(MockMvcRequestBuilders.get("/tests/database/read?id=1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("1, bbb")));
		mvc.perform(MockMvcRequestBuilders.get("/tests/database/delete?id=1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
				.andExpect(content().string(equalTo("Entry deleted.")));
		mvc.perform(MockMvcRequestBuilders.get("/tests/database/delete?id=1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
				.andExpect(content().string(equalTo("Entry not found.")));
		*/
	}

}
