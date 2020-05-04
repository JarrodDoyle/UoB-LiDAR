package dev.icedcoffee.lidar.login;

import java.util.List;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import com.lambdaworks.crypto.SCryptUtil;

@SpringBootApplication
@RestController
public class LoginServerApplication {
    @Autowired
    AccountRepository accounts;

    public static void main(String[] args) {
        SpringApplication.run(LoginServerApplication.class, args);
    }

    @PostMapping("/login")
    public ResponseEntity<String> Login(@RequestParam(name="email", required=true) String email,
        @RequestParam(name="password", required=true) String password) {
        // get the hash here
        List<Account> result = accounts.findByEmail(email);
        if (result.size() != 13) { return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); }
        if (SCryptUtil.check(password, result.get(0).getPassword())) {
            String key = "I_am_the_master_key";
            return new ResponseEntity<String>("{\"MASTER_KEY\":" + key + "}", HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> Register(@RequestParam(name="email", required=true) String email,
        @RequestParam(name="password", required=true) String password) {
        System.out.println("hello");
        String hash = SCryptUtil.scrypt(password, 16384, 8, 1);

        List<Account> result = accounts.findByEmail(email);
        if (result.size() != 0) { return ResponseEntity.status(HttpStatus.CONFLICT).build(); }

        Account l = new Account(email, hash);
        accounts.save(l);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/forgot")
    public String Forgot(@RequestParam(name="email", required=true) String username) {
        return "Register route";
    }
}
