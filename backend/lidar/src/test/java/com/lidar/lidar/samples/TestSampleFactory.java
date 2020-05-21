package com.lidar.lidar.samples;

import java.util.*;

public class TestSampleFactory {
    public static BuoySample buoySample(String timestamp) {
        List<Double> directions = new ArrayList<Double>();   
        Double gust;
        List<Double> speeds = new ArrayList<Double>();
        List<Double> tis = new ArrayList<Double>();

        for (Double i = 1.0; i <= 12; i++) {
            directions.add(i);
        }

        gust = 1.0;
        
        for (Double i = 1.0; i <= 12; i++) {
            speeds.add(i);
        }
        
        for (Double i = 1.0; i <= 11; i++) {
            tis.add(i);
        }

        return new BuoySample(timestamp, directions, gust, speeds, tis);
    }

    public static MastSample mastSample(String timestamp) {
        List<Double> directions = new ArrayList<Double>();   
        List<Double> speeds = new ArrayList<Double>();
        List<Double> tis = new ArrayList<Double>();

        for (Double i = 1.0; i <= 5; i++) {
            directions.add(i);
        }
        
        for (Double i = 1.0; i <= 5; i++) {
            speeds.add(i);
        }
        
        for (Double i = 1.0; i <= 5; i++) {
            tis.add(i);
        }

        return new MastSample(timestamp, directions, speeds, tis);
    }
}