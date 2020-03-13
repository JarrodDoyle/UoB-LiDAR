package com.lidar.lidar;

import com.lidar.lidar.database.*;
import com.lidar.lidar.samples.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

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
    FileLoader fileLoader;

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

    @Autowired
    DirHeightTable dirHeights;

    @Autowired
    SystemManager systemManager;

    public static void main(String[] args) {
        SpringApplication.run(LidarDBServer.class, args);
    }

    @RequestMapping("/test/fileloader")
    public String testFileLoader() {
        fileLoader.loadFiles();
        return "AAAAAAA";
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

    @PostMapping("/database/registermast")
    public String registerMast(@RequestParam(name = "serial", required = true) String serial) {
        Mast mast = new Mast(serial);
        masts.save(mast);
        return "AAAAA";
    }

    @PostMapping("/database/registerbuoy")
    public String registerBuoy(@RequestParam(name = "serial", required = true) String serial, @RequestParam(name = "mast", required = true) String mast) {
        Optional<Mast> maybeMast = masts.findById(mast);
        if (maybeMast.isPresent()) {
            Buoy buoy = new Buoy(serial, maybeMast.get(), speedHeights, dirHeights);
            buoys.save(buoy);
            systemManager.reloadBuoys();
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

    @GetMapping("/database/{serial}/kpis")
    public String getExampleKpis(@PathVariable String serial) {
        Optional<Buoy> maybeBuoy = buoys.findById(serial);
        if (maybeBuoy.isPresent()) {
            return JsonFactory.kpis(maybeBuoy.get());
        }
        else {
            return "Buoy not found.";
        }
    }

    @PostMapping("/database/{serial}/delete") @Transactional
    public String deleteDevice(@PathVariable String serial) {
        Optional<Buoy> maybeBuoy = buoys.findById(serial);
        if (maybeBuoy.isPresent()) {
            List<SpeedHeight> deletedSpeedHeights = new ArrayList<SpeedHeight>();
            deletedSpeedHeights.add(maybeBuoy.get().getSh40());
            deletedSpeedHeights.add(maybeBuoy.get().getSh60());
            deletedSpeedHeights.add(maybeBuoy.get().getSh80());
            deletedSpeedHeights.add(maybeBuoy.get().getSh100());
            buoys.delete(maybeBuoy.get());
            for (SpeedHeight speedHeight : deletedSpeedHeights) {
                speedHeights.delete(speedHeight);
            }
            systemManager.reloadBuoys();
            return "Buoy deleted.";
        }
        else {
            Optional<Mast> maybeMast = masts.findById(serial);
            if (maybeMast.isPresent()) {
                List<SpeedHeight> deletedSpeedHeights = new ArrayList<SpeedHeight>();
                for (Buoy buoy : buoys.findByMast(maybeMast.get())) {
                    deletedSpeedHeights.add(buoy.getSh40());
                    deletedSpeedHeights.add(buoy.getSh60());
                    deletedSpeedHeights.add(buoy.getSh80());
                    deletedSpeedHeights.add(buoy.getSh100());
                }
                buoys.deleteByMast(maybeMast.get());
                for (SpeedHeight speedHeight : deletedSpeedHeights) {
                    speedHeights.delete(speedHeight);
                }
                masts.delete(maybeMast.get());
                systemManager.reloadBuoys();
                return "Mast deleted.";
            }
            else {
                return "Device not found.";
            }
        }
    }

    @PostMapping("/database/{serial}/reset") @Transactional
    public String resetBuoy(@PathVariable String serial) {
        try {
            systemManager.resetBuoy(serial);
            return "Buoy reset.";
        }
        catch (IllegalArgumentException e) {
            return "Buoy not found.";
        }
    }
}