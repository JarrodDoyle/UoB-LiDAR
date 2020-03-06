package com.lidar.lidar;

import java.util.*;

public class ProcTimerTask extends TimerTask {
    private Map<String, BuoyController> buoys;

    private Boolean changed;

    public ProcTimerTask(Map<String, BuoyController> buoys) {
        this.buoys = buoys;
        changed = false;
    }

    public void registerChange() {
        changed = true;
    }

    public void run() {
        if (changed) {
            for (BuoyController buoy : buoys.values()) {
                buoy.processSamples();
            }
            changed = false;
        }
    }
}