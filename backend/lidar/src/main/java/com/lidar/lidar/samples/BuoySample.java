package com.lidar.lidar.samples;

import java.util.*;

public class BuoySample {
    private String timestamp;  
    private List<Double> directions;
    private Double gust;
    private List<Double> speeds;
    private List<Double> tis;

    public BuoySample(String timestamp, List<Double> directions, Double gust, List<Double> speeds, List<Double> tis) {
        this.timestamp = timestamp;
        this.directions = directions;
        this.gust = gust;
        this.speeds = speeds;
        this.tis = tis;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public List<Double> getDirections() {
        return Collections.unmodifiableList(directions);
    }

    public Double getGust() {
        return gust;
    }

    public List<Double> getSpeeds() {
        return Collections.unmodifiableList(speeds);
    }

    public List<Double> getTIs() {
        return Collections.unmodifiableList(tis);
    }

    public String toCSVLine() {
        String line = timestamp;

        for (Integer i = 0; i < directions.size(); i++) {
            line += "," + directions.get(i).toString();
        }
        
        line += "," + gust.toString();

        for (Integer i = 0; i < speeds.size(); i++) {
            line += "," + speeds.get(i).toString();
        }

        for (Integer i = 0; i < tis.size(); i++) {
            line += "," + tis.get(i).toString();
        }

        return line;
    }
}
