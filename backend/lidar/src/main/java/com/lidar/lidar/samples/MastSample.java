package com.lidar.lidar.samples;

import java.util.*;

public class MastSample {
    private String serial;
    private String timestamp;
    private List<Double> directions;
    private List<Double> speeds;
    private List<Double> tis;

    public MastSample(String serial, String timestamp, List<Double> directions, List<Double> speeds, List<Double> tis) {
        this.serial = serial;
        this.timestamp = timestamp;
        this.directions = directions;
        this.speeds = speeds;
        this.tis = tis;
    }

    public String getSerial() {
        return serial;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public List<Double> getDirections() {
        return Collections.unmodifiableList(directions);
    }

    public List<Double> getSpeeds() {
        return Collections.unmodifiableList(speeds);
    }

    public List<Double> getTIs() {
        return Collections.unmodifiableList(tis);
    }
}
