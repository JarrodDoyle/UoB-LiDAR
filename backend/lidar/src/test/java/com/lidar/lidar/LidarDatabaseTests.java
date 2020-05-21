package com.lidar.lidar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;   
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.lidar.lidar.database.*;
import com.lidar.lidar.samples.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class LidarDatabaseTests {
	@Autowired
	private MockMvc mvc;

	@Autowired
	BuoyTable buoys;

	@Autowired
	MastTable masts;

	@Autowired
	SpeedHeightTable speedHeights;

	@Autowired
	DirHeightTable dirHeights;

	/*@Test   
	public void testDatabaseCRUD() throws Exception {
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
	}*/

	@Test
	public void testGetKPIs() throws Exception{

		Mast mast = masts.save(new Mast("TESTMAST"));
		Buoy buoy = new Buoy("TESTBUOY", mast, speedHeights, dirHeights);

		buoy.addSamples(TestSampleFactory.buoySample("2016-06-05T00:00:00Z"), TestSampleFactory.mastSample("2016-06-05T00:00:00Z"));
		
		String jsonString = JsonFactory.kpis(buoy);
		
		buoys.save(buoy);
		buoy.saveData(speedHeights, dirHeights);

		mvc.perform(MockMvcRequestBuilders.get("/database/TESTBUOY/kpis").with(csrf()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(jsonString));
	}

}
