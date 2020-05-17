package com.lidar.lidar.samples;

import java.util.*;

import org.json.simple.*;
import org.json.simple.parser.*;
import com.jayway.jsonpath.*;

public class MastSampleFactory {
    public static MastSample fromCSVLine(String line) throws IllegalArgumentException {
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
                tis.add(Double.parseDouble(entries[i]));
            }
        }
        catch (Exception e) {
            throw new IllegalArgumentException("CSV line not formatted correctly:\n" + line);
        }

        return new MastSample(entries[0], directions, speeds, tis);
    }

    public static MastSample fromJSON(String serial, String jsonString) throws IllegalArgumentException, ParseException {
        JSONParser parser = new JSONParser();

        JSONObject json = (JSONObject) parser.parse(jsonString);

        try {
            List<Double> directions = new ArrayList<Double>();
            directions.add(JsonPath.read(json, "$.direction.h30"));
            directions.add(JsonPath.read(json, "$.direction.h40"));
            directions.add(JsonPath.read(json, "$.direction.h60"));
            directions.add(JsonPath.read(json, "$.direction.h80"));
            directions.add(JsonPath.read(json, "$.direction.h100"));

            List<Double> speeds = new ArrayList<Double>();
            speeds.add(JsonPath.read(json, "$.speed.h30"));
            speeds.add(JsonPath.read(json, "$.speed.h40"));
            speeds.add(JsonPath.read(json, "$.speed.h60"));
            speeds.add(JsonPath.read(json, "$.speed.h80"));
            speeds.add(JsonPath.read(json, "$.speed.h100"));

            List<Double> tis = new ArrayList<Double>();
            tis.add(JsonPath.read(json, "$.ti.h30"));
            tis.add(JsonPath.read(json, "$.ti.h40"));
            tis.add(JsonPath.read(json, "$.ti.h60"));
            tis.add(JsonPath.read(json, "$.ti.h80"));
            tis.add(JsonPath.read(json, "$.ti.h100"));

            return new MastSample(JsonPath.read(json, "$.timestamp"), directions, speeds, tis);
        }
        catch (JsonPathException e) {
            throw new IllegalArgumentException("JSON not formatted correctly.");
        }
    }

    public static List<MastSample> severalFromJSON(String serial, String jsonString) throws IllegalArgumentException, ParseException {
        JSONParser parser = new JSONParser();

        JSONObject json = (JSONObject) parser.parse(jsonString);

        try {
            List<JSONObject> samples = JsonPath.read(json, "$.samples");
    
            List<MastSample> mastSamples = new ArrayList<MastSample>();
    
            for (JSONObject sample : samples) {
                List<Double> directions = new ArrayList<Double>();
                directions.add(JsonPath.read(sample, "$.direction.h30"));
                directions.add(JsonPath.read(sample, "$.direction.h40"));
                directions.add(JsonPath.read(sample, "$.direction.h60"));
                directions.add(JsonPath.read(sample, "$.direction.h80"));
                directions.add(JsonPath.read(sample, "$.direction.h100"));
    
                List<Double> speeds = new ArrayList<Double>();
                speeds.add(JsonPath.read(sample, "$.speed.h30"));
                speeds.add(JsonPath.read(sample, "$.speed.h40"));
                speeds.add(JsonPath.read(sample, "$.speed.h60"));
                speeds.add(JsonPath.read(sample, "$.speed.h80"));
                speeds.add(JsonPath.read(sample, "$.speed.h100"));
    
                List<Double> tis = new ArrayList<Double>();
                tis.add(JsonPath.read(sample, "$.ti.h30"));
                tis.add(JsonPath.read(sample, "$.ti.h40"));
                tis.add(JsonPath.read(sample, "$.ti.h60"));
                tis.add(JsonPath.read(sample, "$.ti.h80"));
                tis.add(JsonPath.read(sample, "$.ti.h100"));
    
                mastSamples.add(new MastSample(JsonPath.read(sample, "$.timestamp"), directions, speeds, tis));
            }

            return mastSamples;
        }
        catch (JsonPathException e) {
            throw new IllegalArgumentException("JSON not formatted correctly.");
        }
    }
}
