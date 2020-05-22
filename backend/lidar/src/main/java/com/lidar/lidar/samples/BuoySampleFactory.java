package com.lidar.lidar.samples;

import java.util.*;

import org.json.simple.*;
import org.json.simple.parser.*;
import com.jayway.jsonpath.*;

public class BuoySampleFactory {
    public static BuoySample fromCSVLine(String line) throws IllegalArgumentException {
        String[] entries = line.split(",");

        List<Double> directions = new ArrayList<Double>();   
        Double gust;
        List<Double> speeds = new ArrayList<Double>();
        List<Double> tis = new ArrayList<Double>();

        try {
            for (Integer i = 1; i <= 12; i++) {
                directions.add(Double.parseDouble(entries[i].replaceAll("^[ \t]+|[ \t]+$", "")));
                if (entries[i].replaceAll("^[ \t]+|[ \t]+$", "").equals("NaN")) {
                    throw new IllegalArgumentException("CSV line not formatted correctly:\n" + line);
                }
            }
            
            gust = Double.parseDouble(entries[13].replaceAll("^[ \t]+|[ \t]+$", ""));
            if (entries[13].replaceAll("^[ \t]+|[ \t]+$", "").equals("NaN")) {
                throw new IllegalArgumentException("CSV line not formatted correctly:\n" + line);
            }
            
            for (Integer i = 14; i <= 25; i++) {
                speeds.add(Double.parseDouble(entries[i].replaceAll("^[ \t]+|[ \t]+$", "")));
                if (entries[i].replaceAll("^[ \t]+|[ \t]+$", "").equals("NaN")) {
                    throw new IllegalArgumentException("CSV line not formatted correctly:\n" + line);
                }
            }
            
            for (Integer i = 26; i <= 36; i++) {
                tis.add(Double.parseDouble(entries[i].replaceAll("^[ \t]+|[ \t]+$", "")));
                if (entries[i].replaceAll("^[ \t]+|[ \t]+$", "").equals("NaN")) {
                    throw new IllegalArgumentException("CSV line not formatted correctly:\n" + line);
                }
            }
        }
        catch (Exception e) {
            throw new IllegalArgumentException("CSV line not formatted correctly:\n" + line);
        }

        return new BuoySample(entries[0].replaceAll("^[ \t]+|[ \t]+$", ""), directions, gust, speeds, tis);
    }

    public static BuoySample fromJSON(String serial, String jsonString) throws IllegalArgumentException, ParseException {
        JSONParser parser = new JSONParser();

        JSONObject json = (JSONObject) parser.parse(jsonString);

        try {
            List<Double> directions = new ArrayList<Double>();
            directions.add(JsonPath.read(json, "$.direction.h4"));
            directions.add(JsonPath.read(json, "$.direction.h30"));
            directions.add(JsonPath.read(json, "$.direction.h40"));
            directions.add(JsonPath.read(json, "$.direction.h40_ref"));
            directions.add(JsonPath.read(json, "$.direction.h60"));
            directions.add(JsonPath.read(json, "$.direction.h80"));
            directions.add(JsonPath.read(json, "$.direction.h100"));
            directions.add(JsonPath.read(json, "$.direction.h120"));
            directions.add(JsonPath.read(json, "$.direction.h140"));
            directions.add(JsonPath.read(json, "$.direction.h160"));
            directions.add(JsonPath.read(json, "$.direction.h180"));
            directions.add(JsonPath.read(json, "$.direction.h200"));

            List<Double> speeds = new ArrayList<Double>();
            speeds.add(JsonPath.read(json, "$.speed.h4"));
            speeds.add(JsonPath.read(json, "$.speed.h30"));
            speeds.add(JsonPath.read(json, "$.speed.h40"));
            speeds.add(JsonPath.read(json, "$.speed.h40_ref"));
            speeds.add(JsonPath.read(json, "$.speed.h60"));
            speeds.add(JsonPath.read(json, "$.speed.h80"));
            speeds.add(JsonPath.read(json, "$.speed.h100"));
            speeds.add(JsonPath.read(json, "$.speed.h120"));
            speeds.add(JsonPath.read(json, "$.speed.h140"));
            speeds.add(JsonPath.read(json, "$.speed.h160"));
            speeds.add(JsonPath.read(json, "$.speed.h180"));
            speeds.add(JsonPath.read(json, "$.speed.h200"));

            List<Double> tis = new ArrayList<Double>();
            tis.add(JsonPath.read(json, "$.ti.h30"));
            tis.add(JsonPath.read(json, "$.ti.h40"));
            tis.add(JsonPath.read(json, "$.ti.h40_ref"));
            tis.add(JsonPath.read(json, "$.ti.h60"));
            tis.add(JsonPath.read(json, "$.ti.h80"));
            tis.add(JsonPath.read(json, "$.ti.h100"));
            tis.add(JsonPath.read(json, "$.ti.h120"));
            tis.add(JsonPath.read(json, "$.ti.h140"));
            tis.add(JsonPath.read(json, "$.ti.h160"));
            tis.add(JsonPath.read(json, "$.ti.h180"));
            tis.add(JsonPath.read(json, "$.ti.h200"));

            return new BuoySample(JsonPath.read(json, "$.timestamp"), directions, JsonPath.read(json, "$.gust"), speeds, tis);
        }
        catch (JsonPathException e) {
            throw new IllegalArgumentException("JSON not formatted correctly.");
        }
    }

    public static List<BuoySample> severalFromJSON(String serial, String jsonString) throws IllegalArgumentException, ParseException {
        JSONParser parser = new JSONParser();

        JSONObject json = (JSONObject) parser.parse(jsonString);

        try {
            List<JSONObject> samples = JsonPath.read(json, "$.samples");
    
            List<BuoySample> buoySamples = new ArrayList<BuoySample>();
    
            for (JSONObject sample : samples) {
                List<Double> directions = new ArrayList<Double>();
                directions.add(JsonPath.read(sample, "$.direction.h4"));
                directions.add(JsonPath.read(sample, "$.direction.h30"));
                directions.add(JsonPath.read(sample, "$.direction.h40"));
                directions.add(JsonPath.read(sample, "$.direction.h40_ref"));
                directions.add(JsonPath.read(sample, "$.direction.h60"));
                directions.add(JsonPath.read(sample, "$.direction.h80"));
                directions.add(JsonPath.read(sample, "$.direction.h100"));
                directions.add(JsonPath.read(sample, "$.direction.h120"));
                directions.add(JsonPath.read(sample, "$.direction.h140"));
                directions.add(JsonPath.read(sample, "$.direction.h160"));
                directions.add(JsonPath.read(sample, "$.direction.h180"));
                directions.add(JsonPath.read(sample, "$.direction.h200"));
    
                List<Double> speeds = new ArrayList<Double>();
                speeds.add(JsonPath.read(sample, "$.speed.h4"));
                speeds.add(JsonPath.read(sample, "$.speed.h30"));
                speeds.add(JsonPath.read(sample, "$.speed.h40"));
                speeds.add(JsonPath.read(sample, "$.speed.h40_ref"));
                speeds.add(JsonPath.read(sample, "$.speed.h60"));
                speeds.add(JsonPath.read(sample, "$.speed.h80"));
                speeds.add(JsonPath.read(sample, "$.speed.h100"));
                speeds.add(JsonPath.read(sample, "$.speed.h120"));
                speeds.add(JsonPath.read(sample, "$.speed.h140"));
                speeds.add(JsonPath.read(sample, "$.speed.h160"));
                speeds.add(JsonPath.read(sample, "$.speed.h180"));
                speeds.add(JsonPath.read(sample, "$.speed.h200"));
    
                List<Double> tis = new ArrayList<Double>();
                tis.add(JsonPath.read(sample, "$.ti.h30"));
                tis.add(JsonPath.read(sample, "$.ti.h40"));
                tis.add(JsonPath.read(sample, "$.ti.h40_ref"));
                tis.add(JsonPath.read(sample, "$.ti.h60"));
                tis.add(JsonPath.read(sample, "$.ti.h80"));
                tis.add(JsonPath.read(sample, "$.ti.h100"));
                tis.add(JsonPath.read(sample, "$.ti.h120"));
                tis.add(JsonPath.read(sample, "$.ti.h140"));
                tis.add(JsonPath.read(sample, "$.ti.h160"));
                tis.add(JsonPath.read(sample, "$.ti.h180"));
                tis.add(JsonPath.read(sample, "$.ti.h200"));
    
                buoySamples.add(new BuoySample(JsonPath.read(sample, "$.timestamp"), directions, JsonPath.read(sample, "$.gust"), speeds, tis));
            }

            return buoySamples;
        }
        catch (JsonPathException e) {
            throw new IllegalArgumentException("JSON not formatted correctly.");
        }
    }
}
