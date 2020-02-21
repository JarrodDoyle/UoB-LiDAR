package com.lidar.lidar;

import com.lidar.lidar.database.*;
import com.lidar.lidar.samples.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class BuoyController {
    Buoy buoy;
    BuoyTable buoys;   

    Queue<BuoySample> buoySamples;
    Queue<MastSample> mastSamples;

    public BuoyController(Buoy buoy, BuoyTable buoys) {
        this.buoy = buoy;
        this.buoys = buoys;

        buoySamples = new LinkedList<BuoySample>();
        mastSamples = new LinkedList<MastSample>();
    }

    public String getMastSerial() {
        return buoy.getMastSerial();
    }

    public void addBuoySample(BuoySample sample) {
        buoySamples.add(sample);
    }

    public void addMastSample(MastSample sample) {
        mastSamples.add(sample);
    }

    public void processSamples() {
        Boolean updated = false;
        while (!buoySamples.isEmpty() && !mastSamples.isEmpty()) {
            BuoySample buoySample = buoySamples.peek();
            MastSample mastSample = mastSamples.peek();
            Integer comparison = compareTimestamps(buoySample, mastSample);
            if (comparison > 0) {
                buoySamples.remove();
            }
            else if (comparison < 0) {
                mastSamples.remove();
            }
            else {
                buoy.addSamples(buoySample, mastSample);
                updated = true;
            }
        }

        if (updated) {
            buoys.save(buoy);
        }
    }

    private Integer compareTimestamps(BuoySample buoySample, MastSample mastSample) {
        ZonedDateTime buoyTime = ZonedDateTime.parse(buoySample.getTimestamp(), DateTimeFormatter.ISO_INSTANT);
        ZonedDateTime mastTime = ZonedDateTime.parse(mastSample.getTimestamp(), DateTimeFormatter.ISO_INSTANT);
        Long separation = buoyTime.until(mastTime, ChronoUnit.MINUTES);
        if (separation <= 5 && separation >= -5) {
            return 0;
        }
        else {
            return buoyTime.compareTo(mastTime);
        }
    }
}
