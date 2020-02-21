package com.lidar.lidar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.setup.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import org.springframework.web.context.*;
import javax.servlet.Filter;
import org.junit.Before;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class LidarDatabaseTests {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private Filter springSecurityFilterChain;

	@Autowired
	private MockMvc mvc;
	
	@Before
	public void setup() {
		
	}

	@Test
	public void testDatabase() throws Exception {
		/*mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.addFilters(springSecurityFilterChain)
				.build();*/
		mvc.perform(MockMvcRequestBuilders.get("/home").accept(MediaType.APPLICATION_JSON));
		mvc.perform(MockMvcRequestBuilders.post("/test/database/create?name=aaa").with(csrf()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
				.andExpect(content().string(matchesPattern("[0-9]*, aaa")));
		mvc.perform(MockMvcRequestBuilders.get("/test/database/read").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
				.andExpect(content().string(matchesPattern("1")));
		mvc.perform(MockMvcRequestBuilders.post("/test/database/delete").with(csrf()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
				.andExpect(content().string(equalTo("All entries deleted.")));
		mvc.perform(MockMvcRequestBuilders.get("/test/database/read").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
				.andExpect(content().string(equalTo("0")));
	}

}
