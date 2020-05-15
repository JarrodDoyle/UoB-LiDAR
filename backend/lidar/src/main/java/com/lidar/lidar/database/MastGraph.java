package com.lidar.lidar.database;

import com.lidar.lidar.samples.*;

import javax.persistence.*;

import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

@Entity @Table(name = "mast_graphs")
public class MastGraph {
    public MastGraph() {

    }

    public MastGraph(GraphConfig config, Mast mast) {
        this.config = config;
        this.mast = mast;

        count = 0l;

        start = null;

        dir30 = 0.0;
        dir40 = 0.0;
        dir60 = 0.0;
        dir80 = 0.0;
        dir100 = 0.0;

        speed30 = 0.0;
        speed40 = 0.0;
        speed60 = 0.0;
        speed80 = 0.0;
        speed100 = 0.0;

        ti30 = 0.0;
        ti40 = 0.0;
        ti60 = 0.0;
        ti80 = 0.0;
        ti100 = 0.0;

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

    @ManyToOne @JoinColumn(name = "mast")
    Mast mast;

    /**
     * @return the buoy
     */
    public Mast getMast() {
        return mast;
    }

    /**
     * @param mast the buoy to set
     */
    public void setMast(Mast mast) {
        this.mast = mast;
    }

    public MastSample addSample(MastSample sample) {
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
                dir30 += directions.get(0);
                dir40 += directions.get(1);
                dir60 += directions.get(2);
                dir80 += directions.get(3);
                dir100 += directions.get(4);
    
                List<Double> speeds = sample.getSpeeds();
                speed30 += speeds.get(0);
                speed40 += speeds.get(1);
                speed60 += speeds.get(2);
                speed80 += speeds.get(3);
                speed100 += speeds.get(4);
    
                List<Double> tis = sample.getTIs();
                ti30 += tis.get(0);
                ti40 += tis.get(1);
                ti60 += tis.get(2);
                ti80 += tis.get(3);
                ti100 += tis.get(4);
    
                rollover = false;
    
                return null;
            }
            else {
                String outTimestamp = sampleTimestamp;
    
                List<Double> outDirections = new ArrayList<Double>();
                outDirections.add(dir30 / count);
                outDirections.add(dir40 / count);
                outDirections.add(dir60 / count);
                outDirections.add(dir80 / count);
                outDirections.add(dir100 / count);
    
                List<Double> outSpeeds = new ArrayList<Double>();
                outSpeeds.add(speed30 / count);
                outSpeeds.add(speed40 / count);
                outSpeeds.add(speed60 / count);
                outSpeeds.add(speed80 / count);
                outSpeeds.add(speed100 / count);
    
                List<Double> outTis = new ArrayList<Double>();
                outTis.add(ti30 / count);
                outTis.add(ti40 / count);
                outTis.add(ti60 / count);
                outTis.add(ti80 / count);
                outTis.add(ti100 / count);
    
                resetFromSample(sample);
    
                start = endTime.toString();
    
                if (!sampleTime.isBefore(pageEndTime)) {
                    rollover = true;
                }
                else {
                    rollover = false;
                }
    
                return new MastSample(outTimestamp, outDirections, outSpeeds, outTis);
            }
        }
    }

    void resetFromSample(MastSample sample) {
        count = 1l;

        sampleTimestamp = sample.getTimestamp();

        List<Double> directions = sample.getDirections();
        dir30 = directions.get(0);
        dir40 = directions.get(1);
        dir60 = directions.get(2);
        dir80 = directions.get(3);
        dir100 = directions.get(4);

        List<Double> speeds = sample.getSpeeds();
        speed30 = speeds.get(0);
        speed40 = speeds.get(1);
        speed60 = speeds.get(2);
        speed80 = speeds.get(3);
        speed100 = speeds.get(4);

        List<Double> tis = sample.getTIs();
        ti30 = tis.get(0);
        ti40 = tis.get(1);
        ti60 = tis.get(2);
        ti80 = tis.get(3);
        ti100 = tis.get(4);
    }

    public void resetGraph() {
        count = 0l;

        start = null;

        dir30 = 0.0;
        dir40 = 0.0;
        dir60 = 0.0;
        dir80 = 0.0;
        dir100 = 0.0;

        speed30 = 0.0;
        speed40 = 0.0;
        speed60 = 0.0;
        speed80 = 0.0;
        speed100 = 0.0;

        ti30 = 0.0;
        ti40 = 0.0;
        ti60 = 0.0;
        ti80 = 0.0;
        ti100 = 0.0;

        pageStart = null;

        sampleTimestamp = null;

        rollover = false;
    }
}