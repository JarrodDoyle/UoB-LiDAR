package com.lidar.lidar.samples;

import java.util.*;

public class BuoySampleFactory {
    public static BuoySample fromCSVLine(String serial, String line) throws IllegalArgumentException {
        String[] entries = line.split(",");

        List<Double> directions = new ArrayList<Double>();   
        Double gust;
        List<Double> speeds = new ArrayList<Double>();
        List<Double> tis = new ArrayList<Double>();

        try {
            for (Integer i = 1; i <= 12; i++) {
                directions.add(Double.parseDouble(entries[i]));
            }

            gust = Double.parseDouble(entries[13]);

            for (Integer i = 14; i <= 25; i++) {
                speeds.add(Double.parseDouble(entries[i]));
            }

            for (Integer i = 26; i <= 36; i++) {
                speeds.add(Double.parseDouble(entries[i]));
            }
        }
        catch (Exception e) {
            throw new IllegalArgumentException("CSV line not formatted correctly.");
        }

        return new BuoySample(serial, entries[0], directions, gust, speeds, tis);
    }
}
