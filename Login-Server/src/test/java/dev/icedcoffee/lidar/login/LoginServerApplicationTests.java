package dev.icedcoffee.lidar.login;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

//@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoginServerApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void contextLoads() {
    }

    @Test
    void registration() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/register")
            .content("email=a%40a%46com&password=a")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            //.with(csrf())
            .accept(MediaType.ALL))
            .andExpect(status().isOk());
    }

    @Test
    void login() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/login")
            .content("email=a%40a.com&password=a")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
           // .with(csrf())
            .accept(MediaType.ALL))
            .andExpect(status().isOk());
    }
}
