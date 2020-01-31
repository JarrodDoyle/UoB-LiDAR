package com.lidar.lidar;

import com.lidar.lidar.database.*;

import java.util.Optional;

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
    MastSampleTable mastSamples;

    @Autowired
    TestTable tests;

    @Autowired
    LoadedFileTable loadedFiles;

    @Autowired
    FileLoader fileLoader;

    public static void main(String[] args) {
        SpringApplication.run(LidarDBServer.class, args);

        
    }

    @RequestMapping("/test/database/create")
    public String testCreate(@RequestParam(name="name", required=false, defaultValue="test") String name) {
        Test test = new Test(name);
        tests.save(test);
        return test.toString();
    }

    @RequestMapping("/test/database/read")
    public String testRead(@RequestParam(name="id", required=false, defaultValue = "1") Long id) {
        Optional<Test> result = tests.findById(id);
        if (result.isPresent()) {
            return result.get().toString();
        }
        else {
            return "Entry not found.";
        }
    }

    @RequestMapping("/test/database/update")
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

    @RequestMapping("/test/database/delete")
    public String testDelete(@RequestParam(name="id", required = false, defaultValue = "1") Long id) {
        if (tests.existsById(id)) {
            tests.deleteById(id);
            return "Entry deleted.";
        }
        else {
            return "Entry not found.";
        }
    }

    @RequestMapping("/test/readfiles")
    public String testReadFiles() {
        fileLoader.loadFiles();
        return "AAAAA";
    }
}