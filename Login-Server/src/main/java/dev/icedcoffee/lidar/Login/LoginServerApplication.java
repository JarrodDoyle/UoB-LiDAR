package dev.icedcoffee.lidar.Login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.lambdaworks.crypto.SCryptUtil;

@SpringBootApplication
@RestController
public class LoginServerApplication {
	@Autowired
	LoginTable logins;

    public static void main(String[] args) {
        SpringApplication.run(LoginServerApplication.class, args);
    }

    @PostMapping("/login")
    public ResponseEntity Login(@RequestParam(name="email", required=true) String email,
            @RequestParam(name="password", required=true) String password) {
        // get the hash here
		Optional<Login> result = logins.findByEmail();
		if (result.notPresent()) { return ResponseEntity.status(401).build(); }
		if (SCryptUtil.check(password, result.getPassword())) {
			String key = "I_am_the_master_key";
			return ResponseEntity.ok().build("{\"MASTER_KEY\":" + key);
		} else {
			return ResponseEntity.status(401).build();
		}
    }

    @PostMapping("/register")
    public ResponseEntity Register(@RequestParam(name="email", required=true) String email,
            @RequestParam(name="password", required=true) String password) {
		String hash = SCryptUtil.scrypt(password, 16384, 8, 1);

		Optional<Login> result = logins.findByEmail();
		if (result.present()) { return ResponseEntity.status(409).build(); }

		Login l = new Login(email, hash);
		logins.save(l);
        return ResponseEntity.ok().build();
    }

	@PostMapping("/forgot")
    public String Forgot(@RequestParam(name="email", required=true) String username) {
        return "Register route";
    }
}
