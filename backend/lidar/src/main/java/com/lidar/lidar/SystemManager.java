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

    Map<String, BuoyController> buoys;

    ProcTimerTask task;

    public void afterPropertiesSet() {
        buoys = new HashMap<String, BuoyController>();
        for (Buoy buoy : buoyTable.findAll()) {
            buoys.put(buoy.getSerial(), new BuoyController(buoy, buoyTable, speedHeights));
        }

        task = new ProcTimerTask(buoys);

        new Timer().scheduleAtFixedRate(task, Constants.processInterval, Constants.processInterval);
    }

    public void reloadBuoys() {
        buoys.clear();
        for (Buoy buoy : buoyTable.findAll()) {
            buoys.put(buoy.getSerial(), new BuoyController(buoy, buoyTable, speedHeights));
        }
    }

    public void addBuoySample(BuoySample sample) {
        buoys.get(sample.getSerial()).addBuoySample(sample);
        task.registerChange();
    }
    
    public void addMastSample(MastSample sample) {
        for (BuoyController buoy : buoys.values()) {
            if (buoy.getMastSerial().equals(sample.getSerial())) {
                buoy.addMastSample(sample);
            }
        }
        task.registerChange();
    }
}
