package com.lidar.lidar;

import com.lidar.lidar.database.*;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import java.io.FileReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

@SpringBootApplication

@RestController
public class LidarDBServer {

    @Autowired
    SampleTable samples;

    @Autowired
    TestTable tests;

    @Autowired
    MastTable masts;

    @Autowired
    BuoyTable buoys;

    @Autowired
    SpeedHeightTable speedHeights;

    public static void main(String[] args) {
        SpringApplication.run(LidarDBServer.class, args);

        
    }

    @PostMapping("/test/database/create")
    public String testCreate(@RequestParam(name="name", required=false, defaultValue="test") String name) {
        Test test = new Test(name);
        tests.save(test);
        return test.toString();
    }

    @GetMapping("/home")
    public String home(HttpServletRequest request) {
        System.out.println(request.getRemoteAddr());
        return "AAAAAAAA";
    }

    @GetMapping("/test/database/read")
    public String testRead() {
        Iterable<Test> result = tests.findAll();
        Integer i = 0;
        for (Test t : result) {
            i++;
        }
        return i.toString();
    }

    @PostMapping("/test/database/update")
    public String testUpdate(@RequestParam(name="id", required = false, defaultValue = "1") Long id, @RequestParam(name="name", required = false, defaultValue = "test") String name) {
        Optional<Test> result = tests.findById(id);
        if (result.isPresent()) {
            Test test = result.get();
            test.setName(name);
            return tests.save(test).toString();
        }
        else {
            return "Entry not found.";
        }
    }

    @PostMapping("/test/database/delete")
    public String testDelete() {
        tests.deleteAll();
        return "All entries deleted.";
    }

    @PostMapping("/test/registermast")
    public String testRegisterMast(@RequestParam(name = "serial", required = true) String serial) {
        Mast mast = new Mast(serial);
        masts.save(mast);
        return "AAAAA";
    }

    @PostMapping("/test/registerbuoy")
    public String testRegisterBuoy(@RequestParam(name = "serial", required = true) String serial, @RequestParam(name = "mast", required = true) String mast) {
        Optional<Mast> maybeMast = masts.findById(mast);
        if (maybeMast.isPresent()) {
            Buoy buoy = new Buoy(serial, maybeMast.get(), speedHeights);
            buoys.save(buoy);
            return "AAAAA";
        }
        else {
            return "Entry not found.";
        }
    }

    @GetMapping("/database/EXBUOY/kpis")
    public String getExampleKpis() {
        return JsonFactory.exampleKpis();
    }
}