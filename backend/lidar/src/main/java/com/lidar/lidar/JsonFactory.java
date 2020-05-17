package com.lidar.lidar;

import java.util.*;
import org.json.simple.*;
import com.lidar.lidar.database.*;
//import org.json.simple.parser.*;

public class JsonFactory {
    public static String kpis(Buoy buoy) {
        JSONObject json = new JSONObject();

        json.put("serial", buoy.getSerial());
        json.put("mast", buoy.getMastSerial());

        JSONObject speed = speed(buoy);
        json.put("speed", speed);
        
        JSONObject direction = direction(buoy);
        json.put("direction", direction);
        
        JSONObject ti = ti();
        json.put("ti", ti);

        return json.toJSONString();
    }

    private static JSONObject speed(Buoy buoy) {
        JSONObject speed = new JSONObject();

        JSONObject h40 = speedKPIs(buoy.getSh40());
        speed.put("h40", h40);

        JSONObject h60 = speedKPIs(buoy.getSh60());
        speed.put("h60", h60);

        JSONObject h80 = speedKPIs(buoy.getSh80());
        speed.put("h80", h80);

        JSONObject h100 = speedKPIs(buoy.getSh100());
        speed.put("h100", h100);

        return speed;
    }

    private static JSONObject speedKPIs(SpeedHeight speedHeight) {        
        JSONObject kpis = new JSONObject();
        
        JSONObject slope = new JSONObject();
        slope.put("a", speedHeight.slopea());
        slope.put("b", speedHeight.slopeb());
        kpis.put("slope", slope);
        
        JSONObject rSqr = new JSONObject();
        rSqr.put("a", speedHeight.rSquareda());
        rSqr.put("b", speedHeight.rSquaredb());
        kpis.put("rSqr", rSqr);
        
        return kpis;
    }

    private static JSONObject direction(Buoy buoy) {
        JSONObject direction = new JSONObject();
        
        JSONObject h40 = directionKPIs(buoy.getDh40());
        direction.put("h40", h40);    

        JSONObject h60 = directionKPIs(buoy.getDh60());
        direction.put("h60", h60);    

        JSONObject h80 = directionKPIs(buoy.getDh80());
        direction.put("h80", h80);    

        JSONObject h100 = directionKPIs(buoy.getDh100());
        direction.put("h100", h100);

        return direction;
    }

    private static JSONObject directionKPIs(DirHeight dirHeight) {
        Random r = new Random();

        JSONObject kpis = new JSONObject();

        JSONObject slope = new JSONObject();
        slope.put("a", dirHeight.slopea());
        slope.put("b", dirHeight.slopeb());
        kpis.put("slope", slope);
        
        JSONObject offset = new JSONObject();
        offset.put("a", dirHeight.intercepta());
        offset.put("b", dirHeight.interceptb());
        kpis.put("offset", offset);

        JSONObject rSqr = new JSONObject();
        rSqr.put("a", dirHeight.rSquareda());
        rSqr.put("b", dirHeight.rSquaredb());
        kpis.put("rSqr", rSqr);

        return kpis;
    }

    private static JSONObject ti() {
        JSONObject ti = new JSONObject();
        
        /*JSONObject h40 = exampleTiKPIs();
        ti.put("h40", h40);    

        JSONObject h60 = exampleTiKPIs();
        ti.put("h60", h60);    

        JSONObject h80 = exampleTiKPIs();
        ti.put("h80", h80);    

        JSONObject h100 = exampleTiKPIs();
        ti.put("h100", h100);*/    

        return ti;
    }

    public static String exampleKpis() {
        JSONObject json = new JSONObject();

        json.put("serial", "EXBUOY");
        json.put("mast", "EXMAST");

        JSONObject speed = exampleSpeed();
        json.put("speed", speed);
        
        JSONObject direction = exampleDirection();
        json.put("direction", direction);
        
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
    
    private static JSONObject exampleDirection() {
        JSONObject direction = new JSONObject();
        
        JSONObject h40 = exampleDirectionKPIs();
        direction.put("h40", h40);    

        JSONObject h60 = exampleDirectionKPIs();
        direction.put("h60", h60);    

        JSONObject h80 = exampleDirectionKPIs();
        direction.put("h80", h80);    

        JSONObject h100 = exampleDirectionKPIs();
        direction.put("h100", h100);    

        return direction;
    }

    private static JSONObject exampleDirectionKPIs() {
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

    public static JSONObject exampleCoverage() {
        JSONObject coverage = new JSONObject();

        coverage.put("serial", "EXBUOY");
        coverage.put("mast", "EXMAST");

        return coverage;
    }
}
