package com.lidar.lidar;

import java.util.*;

import com.lidar.lidar.database.*;
import com.lidar.lidar.samples.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.*;
import org.springframework.stereotype.Controller;

@Controller
public class SystemManager implements InitializingBean {
    @Autowired   
    BuoyTable buoyTable;

    @Autowired
    SpeedHeightTable speedHeights;

    @Autowired
    DirHeightTable dirHeights;

    @Autowired
    GraphManager graphManager;

    Map<String, BuoyController> buoys;

    ProcTimerTask task;

    public void afterPropertiesSet() {
        buoys = new HashMap<String, BuoyController>();
        for (Buoy buoy : buoyTable.findAll()) {
            buoys.put(buoy.getSerial(), new BuoyController(buoy, buoyTable, speedHeights, dirHeights));
        }
    }

    public void reloadBuoys() {
        buoys.clear();
        for (Buoy buoy : buoyTable.findAll()) {
            buoys.put(buoy.getSerial(), new BuoyController(buoy, buoyTable, speedHeights, dirHeights));
        }
    }

    public void addBuoySample(String serial, BuoySample sample) {
        BuoyController buoy = buoys.get(serial);
        buoy.addBuoySample(sample);
        graphManager.AddBuoySample(serial, sample);
        buoy.processSamples();
        graphManager.processSamples();
    }
    
    public void addBuoySamples(String serial, List<BuoySample> samples) {
        BuoyController buoy = buoys.get(serial);
        for (BuoySample sample : samples) {
            buoy.addBuoySample(sample);
            graphManager.AddBuoySample(serial, sample);
        }
        buoy.processSamples();
        graphManager.processSamples();
    }
    
    public void addMastSample(String serial, MastSample sample) {
        for (BuoyController buoy : buoys.values()) {
            if (buoy.getMastSerial().equals(serial)) {
                buoy.addMastSample(sample);
                buoy.processSamples();
            }
        }
        graphManager.AddMastSample(serial, sample);
        graphManager.processSamples();
    }
    
    public void addMastSamples(String serial, List<MastSample> samples) {
        for (BuoyController buoy : buoys.values()) {
            if (buoy.getMastSerial().equals(serial)) {
                for (MastSample sample : samples) {
                    buoy.addMastSample(sample);
                }
                buoy.processSamples();
            }
        }
        for (MastSample sample : samples) {
            graphManager.AddMastSample(serial, sample);
        }
        graphManager.processSamples();
    }

    public void resetBuoy(String serial) {
        if (buoys.containsKey(serial)) {
            buoys.get(serial).reset();
        }
        else {
            throw new IllegalArgumentException("Buoy does not exist.");
        }
    }

    public void resetAll() {
        for (BuoyController buoy : buoys.values()) {
            buoy.reset();
        }
    }
}
