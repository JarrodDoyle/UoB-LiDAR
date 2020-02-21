package dev.icedcoffee.lidar.Login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RestController
public class LoginServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginServerApplication.class, args);
    }

    @PostMapping("/login")
    public String testCreate(@RequestParam(name="username", required=true) String username,
            @RequestParam(name="password", required=true) String password) {
        return "Login route"
    }
}
