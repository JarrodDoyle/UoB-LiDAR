package com.lidar.lidar;

import java.util.*;
import org.json.simple.*;
//import org.json.simple.parser.*;

public class JsonFactory {
    public static String kpis(BuoyController buoy) {
        JSONObject json = new JSONObject();

        return json.toJSONString();
    }

    public static String exampleKpis() {
        JSONObject json = new JSONObject();

        json.put("serial", "EXBUOY");
        json.put("mast", "EXMAST");

        JSONObject speed = exampleSpeed();
        json.put("speed", speed);
        
        JSONObject distance = exampleDistance();
        json.put("distance", distance);
        
        JSONObject ti = exampleTi();
        json.put("ti", ti);

        return json.toJSONString();
    }

    private static JSONObject exampleSpeed() {
        JSONObject speed = new JSONObject();

        JSONObject h40 = exampleSpeedKPIs();
        speed.put("h40", h40);

        JSONObject h60 = exampleSpeedKPIs();
        speed.put("h60", h60);

        JSONObject h80 = exampleSpeedKPIs();
        speed.put("h80", h80);

        JSONObject h100 = exampleSpeedKPIs();
        speed.put("h100", h100);

        return speed;
    }
    
    private static JSONObject exampleSpeedKPIs() {
        Random r = new Random();
        
        JSONObject kpis = new JSONObject();
        
        JSONObject slope = new JSONObject();
        slope.put("a", 0.96d + 0.08d * r.nextDouble());
        slope.put("b", 0.96d + 0.08d * r.nextDouble());
        kpis.put("slope", slope);
        
        JSONObject rSqr = new JSONObject();
        rSqr.put("a", 0.96d + 0.04d * r.nextDouble());
        rSqr.put("b", 0.96d + 0.04d * r.nextDouble());
        kpis.put("rSqr", rSqr);
        
        return kpis;
    }
    
    private static JSONObject exampleDistance() {
        JSONObject distance = new JSONObject();
        
        JSONObject h40 = exampleDistanceKPIs();
        distance.put("h40", h40);    

        JSONObject h60 = exampleDistanceKPIs();
        distance.put("h60", h60);    

        JSONObject h80 = exampleDistanceKPIs();
        distance.put("h80", h80);    

        JSONObject h100 = exampleDistanceKPIs();
        distance.put("h100", h100);    

        return distance;
    }

    private static JSONObject exampleDistanceKPIs() {
        Random r = new Random();

        JSONObject kpis = new JSONObject();

        JSONObject slope = new JSONObject();
        slope.put("a", 0.94d + 0.12d * r.nextDouble());
        slope.put("b", 0.94d + 0.12d * r.nextDouble());
        kpis.put("slope", slope);
        
        JSONObject offset = new JSONObject();
        offset.put("a", 4d + 7d * r.nextDouble());
        offset.put("b", 4d + 7d * r.nextDouble());
        kpis.put("offset", offset);

        JSONObject rSqr = new JSONObject();
        rSqr.put("a", 0.94d + 0.06d * r.nextDouble());
        rSqr.put("b", 0.94d + 0.06d * r.nextDouble());
        kpis.put("rSqr", rSqr);

        return kpis;
    }
    
    private static JSONObject exampleTi() {
        JSONObject ti = new JSONObject();
        
        JSONObject h40 = exampleTiKPIs();
        ti.put("h40", h40);    

        JSONObject h60 = exampleTiKPIs();
        ti.put("h60", h60);    

        JSONObject h80 = exampleTiKPIs();
        ti.put("h80", h80);    

        JSONObject h100 = exampleTiKPIs();
        ti.put("h100", h100);    

        return ti;
    }

    private static JSONObject exampleTiKPIs() {
        Random r = new Random();

        JSONObject kpis = new JSONObject();

        JSONObject slope = new JSONObject();
        kpis.put("slope", 0.94d + 0.12d * r.nextDouble());

        JSONObject rSqr = new JSONObject();
        kpis.put("rSqr", 0.94d + 0.06d * r.nextDouble());

        return kpis;
    }
}