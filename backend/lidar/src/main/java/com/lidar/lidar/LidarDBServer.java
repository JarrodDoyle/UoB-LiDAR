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

import org.json.simple.*;
import org.json.simple.parser.*;
import com.jayway.jsonpath.JsonPath;

import org.springframework.web.bind.annotation.*;

@SpringBootApplication

@RestController
public class LidarDBServer {

    @Autowired
    FileLoader fileLoader;

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
    GraphConfigTable graphConfigs;

    @Autowired
    MastGraphTable mastGraphs;

    @Autowired
    BuoyGraphTable buoyGraphs;

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
    public String testCreate(@RequestParam(name="name", defaultValue="test") String name) {
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
    public String testUpdate(@RequestParam(name="id", defaultValue = "1") Long id, @RequestParam(name="name", defaultValue = "test") String name) {
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

    @PostMapping("/database/graphs/listconfigs")
    public String listGraphConfigs() {
        JSONObject json = new JSONObject();

        JSONArray graphList = new JSONArray();

        for (GraphConfig config : graphConfigs.findAll()) {
            graphList.add(config.getName());
        }

        json.put("configs", graphList);

        return json.toJSONString();
    }

    @PostMapping("/database/graphs/addconfig")
    public String addGraphConfig(@RequestParam(name = "name") String name, @RequestParam(name = "pagelength") Long pageLength, @RequestParam(name = "datalength") Long dataLength) {

        GraphConfig config = new GraphConfig(name, pageLength, dataLength);
        graphConfigs.save(config);

        return "AAAAAA";
    }

    @PostMapping("/database/registermast")
    public String registerMast(@RequestParam(name = "serial") String serial, @RequestParam(name = "overwrite", defaultValue = "false") Boolean overwrite) {
        if (!masts.existsById(serial) || overwrite) {
            Mast mast = new Mast(serial);
            masts.save(mast);
            return "AAAAA";
        }
        else {
            return "Mast already exists.";
        }
    }

    @PostMapping("/database/registerbuoy")
    public String registerBuoy(@RequestParam(name = "serial") String serial, @RequestParam(name = "mast") String mast, @RequestParam(name = "overwrite", defaultValue = "false") Boolean overwrite) {
        Optional<Mast> maybeMast = masts.findById(mast);
        if (maybeMast.isPresent()) {
            if (!buoys.existsById(serial) || overwrite) {
                Buoy buoy = new Buoy(serial, maybeMast.get(), speedHeights, dirHeights);
                buoys.save(buoy);
                systemManager.reloadBuoys();
                return "AAAAA";
            }
            else {
                return "Buoy already exists.";
            }
        }
        else {
            return "Mast not found.";
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

    @PostMapping("/database/{serial}/registergraph") @Transactional
    public String registerGraph(@PathVariable String serial, @RequestParam(name = "configname") String configName) {
        List<GraphConfig> configs = graphConfigs.findByName(configName);
        if (configs.size() == 0) return "Config not found.";

        Optional<Buoy> maybeBuoy = buoys.findById(serial);
        if (!maybeBuoy.isPresent()) {
            Optional<Mast> maybeMast = masts.findById(serial);
            if (!maybeMast.isPresent()) {
                return "Device not found.";
            }            
            else {
                MastGraph graph = new MastGraph(configs.get(0), maybeMast.get());
                mastGraphs.save(graph);
            }
        }
        else {
            BuoyGraph graph = new BuoyGraph(configs.get(0), maybeBuoy.get());
            buoyGraphs.save(graph);
        }

        return "AAAAA";
    }

    @PostMapping("/database/{serial}/sendjson") @Transactional
    public String sendSample(@PathVariable String serial, @RequestBody String body) {
        Optional<Buoy> maybeBuoy = buoys.findById(serial);
        if (maybeBuoy.isPresent()) {
            JSONParser parser = new JSONParser();
            try {
                JSONObject json = (JSONObject) parser.parse(body);

                if (json.containsKey("samples")) {
                    systemManager.addBuoySamples(serial, BuoySampleFactory.severalFromJSON(serial, body));
                }
                else {
                    systemManager.addBuoySample(serial, BuoySampleFactory.fromJSON(serial, body));
                }

                return "dfghjk";
            }
            catch (ParseException e) {
                return "Could not parse json.";
            }

        }
        else {
            Optional<Mast> maybeMast = masts.findById(serial);
            if (maybeMast.isPresent()) {
                JSONParser parser = new JSONParser();
                try {
                    JSONObject json = (JSONObject) parser.parse(body);

                    if (json.containsKey("samples")) {
                        systemManager.addMastSamples(serial, MastSampleFactory.severalFromJSON(serial, body));
                    }
                    else {
                        systemManager.addMastSample(serial, MastSampleFactory.fromJSON(serial, body));
                    }

                    return "dfghjk";
                }
                catch (ParseException e) {
                    return "Could not parse json.";
                }
            }
            else {
                return "Device not found.";
            }
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
    public String resetDevice(@PathVariable String serial) {
        try {
            systemManager.resetBuoy(serial);

            for (BuoyGraph graph : buoyGraphs.findByBuoy(buoys.findById(serial).get())) {
                graph.resetGraph();
            }

            return "Buoy reset.";
        }
        catch (IllegalArgumentException e) {
            Optional<Mast> maybeMast = masts.findById(serial);
            if (maybeMast.isPresent()) {
                for (MastGraph graph : mastGraphs.findByMast(maybeMast.get())) {
                    graph.resetGraph();
                }

                return "Mast reset.";
            }
            else return "Device not found.";
        }
    }

    @PostMapping("/database/resetall") @Transactional
    public String resetAll() {
        systemManager.resetAll();
        
        for (BuoyGraph graph : buoyGraphs.findAll()) {
            graph.resetGraph();
        }

        for (MastGraph graph : mastGraphs.findAll()) {
            graph.resetGraph();
        }

        return "All devices reset.";
    }
}