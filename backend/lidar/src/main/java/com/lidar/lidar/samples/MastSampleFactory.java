package com.lidar.lidar.samples;

import java.util.*;

public class MastSampleFactory {
    public static MastSample fromCSVLine(String serial, String line) throws IllegalArgumentException {
        String[] entries = line.split(",");

        List<Double> directions = new ArrayList<Double>();   
        List<Double> speeds = new ArrayList<Double>();
        List<Double> tis = new ArrayList<Double>();

        try {
            for (Integer i = 1; i <= 5; i++) {
                directions.add(Double.parseDouble(entries[i]));
            }

            for (Integer i = 6; i <= 10; i++) {
                speeds.add(Double.parseDouble(entries[i]));
            }

            for (Integer i = 11; i <= 15; i++) {
                speeds.add(Double.parseDouble(entries[i]));
            }
        }
        catch (Exception e) {
            throw new IllegalArgumentException("CSV line not formatted correctly for " + serial);
        }

        return new MastSample(serial, entries[0], directions, speeds, tis);
    }
}
