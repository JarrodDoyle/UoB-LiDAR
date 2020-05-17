package com.lidar.lidar.database;

import com.lidar.lidar.samples.*;

import javax.persistence.*;

import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

@Entity @Table(name = "buoy_graphs")
public class BuoyGraph {
    public BuoyGraph() {

    }

    public BuoyGraph(GraphConfig config, Buoy buoy) {
        this.config = config;
        this.buoy = buoy;

        count = 0l;

        start = null;

        dir4 = 0.0;
        dir30 = 0.0;
        dir40 = 0.0;
        dir40ref = 0.0;
        dir60 = 0.0;
        dir80 = 0.0;
        dir100 = 0.0;
        dir120 = 0.0;
        dir140 = 0.0;
        dir160 = 0.0;
        dir180 = 0.0;
        dir200 = 0.0;
        
        gust = 0.0;

        speed4 = 0.0;
        speed30 = 0.0;
        speed40 = 0.0;
        speed40ref = 0.0;
        speed60 = 0.0;
        speed80 = 0.0;
        speed100 = 0.0;
        speed120 = 0.0;
        speed140 = 0.0;
        speed160 = 0.0;
        speed180 = 0.0;
        speed200 = 0.0;

        ti30 = 0.0;
        ti40 = 0.0;
        ti40ref = 0.0;
        ti60 = 0.0;
        ti80 = 0.0;
        ti100 = 0.0;
        ti120 = 0.0;
        ti140 = 0.0;
        ti160 = 0.0;
        ti180 = 0.0;
        ti200 = 0.0;

        pageStart = null;

        sampleTimestamp = null;

        rollover = false;
    }

    @Id @GeneratedValue @Column(name = "id")
    Long id;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne @JoinColumn(name = "config")
    GraphConfig config;

    /**
     * @return the config
     */
    public GraphConfig getConfig() {
        return config;
    }

    /**
     * @param config the config to set
     */
    public void setConfig(GraphConfig config) {
        this.config = config;
    }

    @Column(name = "dir4")
    Double dir4;

    public Double getDir4() {
        return dir4;
    }

    public void setDir4(Double dir4) {
        this.dir4 = dir4;
    }

    @Column(name = "dir30")
    Double dir30;

    public Double getDir30() {
        return dir30;
    }

    public void setDir30(Double dir30) {
        this.dir30 = dir30;
    }

    @Column(name = "dir40")
    Double dir40;

    public Double getDir40() {
        return dir40;
    }

    public void setDir40(Double dir40) {
        this.dir40 = dir40;
    }
    
    @Column(name = "dir40_ref")
    Double dir40ref;
    
    public Double getDir40ref() {
        return dir40ref;
    }
    
    public void setDir40ref(Double dir40ref) {
        this.dir40ref = dir40ref;
    }

    @Column(name = "dir60")
    Double dir60;

    public Double getDir60() {
        return dir60;
    }

    public void setDir60(Double dir60) {
        this.dir60 = dir60;
    }

    @Column(name = "dir80")
    Double dir80;

    public Double getDir80() {
        return dir80;
    }

    public void setDir80(Double dir80) {
        this.dir80 = dir80;
    }

    @Column(name = "dir100")
    Double dir100;

    public Double getDir100() {
        return dir100;
    }

    public void setDir100(Double dir100) {
        this.dir100 = dir100;
    }

    @Column(name = "dir120")
    Double dir120;

    public Double getDir120() {
        return dir120;
    }

    public void setDir120(Double dir120) {
        this.dir120 = dir120;
    }

    @Column(name = "dir140")
    Double dir140;

    public Double getDir140() {
        return dir140;
    }

    public void setDir140(Double dir140) {
        this.dir140 = dir140;
    }

    @Column(name = "dir160")
    Double dir160;

    public Double getDir160() {
        return dir160;
    }

    public void setDir160(Double dir160) {
        this.dir160 = dir160;
    }

    @Column(name = "dir180")
    Double dir180;

    public Double getDir180() {
        return dir180;
    }

    public void setDir180(Double dir180) {
        this.dir180 = dir180;
    }

    @Column(name = "dir200")
    Double dir200;

    public Double getDir200() {
        return dir200;
    }

    public void setDir200(Double dir200) {
        this.dir200 = dir200;
    }
    
    @Column(name = "gust")
    Double gust;
    
    public Double getGust() {
        return gust;
    }

    @Column(name = "speed4")
    Double speed4;

    public Double getSpeed4() {
        return speed4;
    }

    public void setSpeed4(Double speed4) {
        this.speed4 = speed4;
    }

    @Column(name = "speed30")
    Double speed30;

    public Double getSpeed30() {
        return speed30;
    }

    public void setSpeed30(Double speed30) {
        this.speed30 = speed30;
    }

    @Column(name = "speed40")
    Double speed40;

    public Double getSpeed40() {
        return speed40;
    }

    public void setSpeed40(Double speed40) {
        this.speed40 = speed40;
    }
    
    @Column(name = "speed40_ref")
    Double speed40ref;
    
    public Double getSpeed40ref() {
        return speed40ref;
    }
    
    public void setSpeed40ref(Double speed40ref) {
        this.speed40ref = speed40ref;
    }

    @Column(name = "speed60")
    Double speed60;

    public Double getSpeed60() {
        return speed60;
    }

    public void setSpeed60(Double speed60) {
        this.speed60 = speed60;
    }

    @Column(name = "speed80")
    Double speed80;

    public Double getSpeed80() {
        return speed80;
    }

    public void setSpeed80(Double speed80) {
        this.speed80 = speed80;
    }

    @Column(name = "speed100")
    Double speed100;

    public Double getSpeed100() {
        return speed100;
    }

    public void setSpeed100(Double speed100) {
        this.speed100 = speed100;
    }

    @Column(name = "speed120")
    Double speed120;

    public Double getSpeed120() {
        return speed120;
    }

    public void setSpeed120(Double speed120) {
        this.speed120 = speed120;
    }

    @Column(name = "speed140")
    Double speed140;

    public Double getSpeed140() {
        return speed140;
    }

    public void setSpeed140(Double speed140) {
        this.speed140 = speed140;
    }

    @Column(name = "speed160")
    Double speed160;

    public Double getSpeed160() {
        return speed160;
    }

    public void setSpeed160(Double speed160) {
        this.speed160 = speed160;
    }

    @Column(name = "speed180")
    Double speed180;

    public Double getSpeed180() {
        return speed180;
    }

    public void setSpeed180(Double speed180) {
        this.speed180 = speed180;
    }

    @Column(name = "speed200")
    Double speed200;

    public Double getSpeed200() {
        return speed200;
    }

    public void setSpeed200(Double speed200) {
        this.speed200 = speed200;
    }

    @Column(name = "ti30")
    Double ti30;

    public Double getTi30() {
        return ti30;
    }

    public void setTi30(Double ti30) {
        this.ti30 = ti30;
    }

    @Column(name = "ti40")
    Double ti40;

    public Double getTi40() {
        return ti40;
    }

    public void setTi40(Double ti40) {
        this.ti40 = ti40;
    }
    
    @Column(name = "ti40_ref")
    Double ti40ref;
    
    public Double getTi40ref() {
        return ti40ref;
    }
    
    public void setTi40ref(Double ti40ref) {
        this.ti40ref = ti40ref;
    }

    @Column(name = "ti60")
    Double ti60;

    public Double getTi60() {
        return ti60;
    }

    public void setTi60(Double ti60) {
        this.ti60 = ti60;
    }

    @Column(name = "ti80")
    Double ti80;

    public Double getTi80() {
        return ti80;
    }

    public void setTi80(Double ti80) {
        this.ti80 = ti80;
    }

    @Column(name = "ti100")
    Double ti100;

    public Double getTi100() {
        return ti100;
    }

    public void setTi100(Double ti100) {
        this.ti100 = ti100;
    }

    @Column(name = "ti120")
    Double ti120;

    public Double getTi120() {
        return ti120;
    }

    public void setTi120(Double ti120) {
        this.ti120 = ti120;
    }

    @Column(name = "ti140")
    Double ti140;

    public Double getTi140() {
        return ti140;
    }

    public void setTi140(Double ti140) {
        this.ti140 = ti140;
    }

    @Column(name = "ti160")
    Double ti160;

    public Double getTi160() {
        return ti160;
    }

    public void setTi160(Double ti160) {
        this.ti160 = ti160;
    }

    @Column(name = "ti180")
    Double ti180;

    public Double getTi180() {
        return ti180;
    }

    public void setTi180(Double ti180) {
        this.ti180 = ti180;
    }

    @Column(name = "ti200")
    Double ti200;

    public Double getTi200() {
        return ti200;
    }

    public void setTi200(Double ti200) {
        this.ti200 = ti200;
    }

    public void setGust(Double gust) {
        this.gust = gust;
    }

    @Column(name = "count")
    Long count;

    /**
     * @return the count
     */
    public Long getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(Long count) {
        this.count = count;
    }

    @Column(name = "start")
    String start;

    /**
     * @return the start
     */
    public String getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(String start) {
        this.start = start;
    }

    @Column(name = "page_start")
    String pageStart;

    /**
     * @return the pageStart
     */
    public String getPageStart() {
        return pageStart;
    }

    /**
     * @param pageStart the pageStart to set
     */
    public void setPageStart(String pageStart) {
        this.pageStart = pageStart;
    }

    @Column(name = "sample_time")
    String sampleTimestamp;

    /**
     * @return the sampleTimestamp
     */
    public String getSampleTimestamp() {
        return sampleTimestamp;
    }

    /**
     * @param sampleTime the sampleTime to set
     */
    public void setSampleTimestamp(String sampleTimestamp) {
        this.sampleTimestamp = sampleTimestamp;
    }

    @Column(name = "rollover")
    Boolean rollover;

    /**
     * @return the rollover
     */
    public Boolean getRollover() {
        return rollover;
    }

    /**
     * @param rollover the rollover to set
     */
    public void setRollover(Boolean rollover) {
        this.rollover = rollover;
    }

    @ManyToOne @JoinColumn(name = "buoy")
    Buoy buoy;

    /**
     * @return the buoy
     */
    public Buoy getBuoy() {
        return buoy;
    }

    /**
     * @param buoy the buoy to set
     */
    public void setBuoy(Buoy buoy) {
        this.buoy = buoy;
    }

    public BuoySample addSample(BuoySample sample) {
        if (start == null || pageStart == null) {
            start = sample.getTimestamp();
            pageStart = sample.getTimestamp();
            resetFromSample(sample);
            return null;
        }
        else {
            Instant startTime = Instant.from(DateTimeFormatter.ISO_INSTANT.parse(start));
            Instant pageStartTime = Instant.from(DateTimeFormatter.ISO_INSTANT.parse(pageStart));
            Instant sampleTime = Instant.from(DateTimeFormatter.ISO_INSTANT.parse(sample.getTimestamp()));
            Instant endTime = startTime.plus(Duration.ofMinutes(config.getDataLength()));
            Instant pageEndTime = pageStartTime.plus(Duration.ofDays(config.getPageLength()));
    
            if (rollover == true) {
                pageStartTime = pageEndTime;
                pageEndTime = pageEndTime.plus(Duration.ofDays(config.getPageLength()));
                pageStart = pageStartTime.toString();
            }
    
            if (endTime.isAfter(sampleTime)) {
                count++;
    
                if (sampleTimestamp == null) {
                    sampleTimestamp = sample.getTimestamp();
                }
                else {
                    Instant oldSampleTime = Instant.from(DateTimeFormatter.ISO_INSTANT.parse(sampleTimestamp));
                    sampleTimestamp = Instant.ofEpochSecond((oldSampleTime.getEpochSecond() * (count - 1)) / count + sampleTime.getEpochSecond() / count).toString();
                }
                
                List<Double> directions = sample.getDirections();
                dir4 += directions.get(0);
                dir30 += directions.get(1);
                dir40 += directions.get(2);
                dir40ref += directions.get(3);
                dir60 += directions.get(4);
                dir80 += directions.get(5);
                dir100 += directions.get(6);
                dir120 += directions.get(7);
                dir140 += directions.get(8);
                dir160 += directions.get(9);
                dir180 += directions.get(10);
                dir200 += directions.get(11);
                
                gust += sample.getGust();
                
                List<Double> speeds = sample.getSpeeds();
                speed4 += speeds.get(0);
                speed30 += speeds.get(1);
                speed40 += speeds.get(2);
                speed40ref += speeds.get(3);
                speed60 += speeds.get(4);
                speed80 += speeds.get(5);
                speed100 += speeds.get(6);
                speed120 += speeds.get(7);
                speed140 += speeds.get(8);
                speed160 += speeds.get(9);
                speed180 += speeds.get(10);
                speed200 += speeds.get(11);
                
                List<Double> tis = sample.getTIs();
                ti30 += tis.get(0);
                ti40 += tis.get(1);
                ti40ref += tis.get(2);
                ti60 += tis.get(3);
                ti80 += tis.get(4);
                ti100 += tis.get(5);
                ti120 += tis.get(6);
                ti140 += tis.get(7);
                ti160 += tis.get(8);
                ti180 += tis.get(9);
                ti200 += tis.get(10);
                
                rollover = false;
                
                return null;
            }
            else {
                String outTimestamp = sampleTimestamp;
                
                List<Double> outDirections = new ArrayList<Double>();
                outDirections.add(dir4 / count);
                outDirections.add(dir30 / count);
                outDirections.add(dir40 / count);
                outDirections.add(dir40ref / count);
                outDirections.add(dir60 / count);
                outDirections.add(dir80 / count);
                outDirections.add(dir100 / count);
                outDirections.add(dir120 / count);
                outDirections.add(dir140 / count);
                outDirections.add(dir160 / count);
                outDirections.add(dir180 / count);
                outDirections.add(dir200 / count);
    
                Double outGust = gust / count;
    
                List<Double> outSpeeds = new ArrayList<Double>();
                outSpeeds.add(speed4 / count);
                outSpeeds.add(speed30 / count);
                outSpeeds.add(speed40 / count);
                outSpeeds.add(speed40ref / count);
                outSpeeds.add(speed60 / count);
                outSpeeds.add(speed80 / count);
                outSpeeds.add(speed100 / count);
                outSpeeds.add(speed120 / count);
                outSpeeds.add(speed140 / count);
                outSpeeds.add(speed160 / count);
                outSpeeds.add(speed180 / count);
                outSpeeds.add(speed200 / count);
    
                List<Double> outTis = new ArrayList<Double>();
                outTis.add(ti30 / count);
                outTis.add(ti40 / count);
                outTis.add(ti40ref / count);
                outTis.add(ti60 / count);
                outTis.add(ti80 / count);
                outTis.add(ti100 / count);
                outTis.add(ti120 / count);
                outTis.add(ti140 / count);
                outTis.add(ti160 / count);
                outTis.add(ti180 / count);
                outTis.add(ti200 / count);
    
                resetFromSample(sample);

                start = endTime.toString();
    
                if (!sampleTime.isBefore(pageEndTime)) {
                    rollover = true;
                }
                else {
                    rollover = false;
                }
    
                return new BuoySample(outTimestamp, outDirections, outGust, outSpeeds, outTis);
            }
        }

    }

    void resetFromSample(BuoySample sample) {
        count = 1l;

        sampleTimestamp = sample.getTimestamp();

        List<Double> directions = sample.getDirections();
        dir4 = directions.get(0);
        dir30 = directions.get(1);
        dir40 = directions.get(2);
        dir40ref = directions.get(3);
        dir60 = directions.get(4);
        dir80 = directions.get(5);
        dir100 = directions.get(6);
        dir120 = directions.get(7);
        dir140 = directions.get(8);
        dir160 = directions.get(9);
        dir180 = directions.get(10);
        dir200 = directions.get(11);

        gust = sample.getGust();

        List<Double> speeds = sample.getSpeeds();
        speed4 = speeds.get(0);
        speed30 = speeds.get(1);
        speed40 = speeds.get(2);
        speed40ref = speeds.get(3);
        speed60 = speeds.get(4);
        speed80 = speeds.get(5);
        speed100 = speeds.get(6);
        speed120 = speeds.get(7);
        speed140 = speeds.get(8);
        speed160 = speeds.get(9);
        speed180 = speeds.get(10);
        speed200 = speeds.get(11);

        List<Double> tis = sample.getTIs();
        ti30 = tis.get(0);
        ti40 = tis.get(1);
        ti40ref = tis.get(2);
        ti60 = tis.get(3);
        ti80 = tis.get(4);
        ti100 = tis.get(5);
        ti120 = tis.get(6);
        ti140 = tis.get(7);
        ti160 = tis.get(8);
        ti180 = tis.get(9);
        ti200 = tis.get(10);
    }

    public void resetGraph() {
        count = 0l;

        start = null;

        dir4 = 0.0;
        dir30 = 0.0;
        dir40 = 0.0;
        dir40ref = 0.0;
        dir60 = 0.0;
        dir80 = 0.0;
        dir100 = 0.0;
        dir120 = 0.0;
        dir140 = 0.0;
        dir160 = 0.0;
        dir180 = 0.0;
        dir200 = 0.0;
        
        gust = 0.0;

        speed4 = 0.0;
        speed30 = 0.0;
        speed40 = 0.0;
        speed40ref = 0.0;
        speed60 = 0.0;
        speed80 = 0.0;
        speed100 = 0.0;
        speed120 = 0.0;
        speed140 = 0.0;
        speed160 = 0.0;
        speed180 = 0.0;
        speed200 = 0.0;

        ti30 = 0.0;
        ti40 = 0.0;
        ti40ref = 0.0;
        ti60 = 0.0;
        ti80 = 0.0;
        ti100 = 0.0;
        ti120 = 0.0;
        ti140 = 0.0;
        ti160 = 0.0;
        ti180 = 0.0;
        ti200 = 0.0;

        pageStart = null;

        sampleTimestamp = null;

        rollover = false;
    }
}