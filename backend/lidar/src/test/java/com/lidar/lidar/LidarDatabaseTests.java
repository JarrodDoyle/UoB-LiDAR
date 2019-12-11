package com.lidar.lidar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.equalTo;
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
	public void testCreate() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/tests/database/create?name=aaa").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
				.andExpect(content().string(equalTo("1, aaa")));
		mvc.perform(MockMvcRequestBuilders.get("/tests/database/read?id=1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
				.andExpect(content().string(equalTo("1, aaa")));
		mvc.perform(MockMvcRequestBuilders.get("/tests/database/read?id=2").accept(MediaType.APPLICATION_JSON))
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
	}

}
