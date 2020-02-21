package com.lidar.lidar;

import java.util.*;

import com.lidar.lidar.database.*;
import com.lidar.lidar.samples.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SystemManager {
    @Autowired   
    BuoyTable buoyTable;

    Map<String, BuoyController> buoys;

    public SystemManager() {
        
    }

    public void setup() {
        buoys = new HashMap<String, BuoyController>();
        for (Buoy buoy : buoyTable.findAll()) {
            buoys.put(buoy.getSerial(), new BuoyController(buoy, buoyTable));
        }
    }

    public void addBuoySample(BuoySample sample) {
        buoys.get(sample.getSerial()).addBuoySample(sample);
    }

    public void addMastSample(MastSample sample) {
        for (BuoyController buoy : buoys.values()) {
            if (buoy.getMastSerial().equals(sample.getSerial())) {
                buoy.addMastSample(sample);
            }
        }
    }
    
    public void processSamples() {
        for (BuoyController buoy : buoys.values()) {
            buoy.processSamples();
        }
    }
}
