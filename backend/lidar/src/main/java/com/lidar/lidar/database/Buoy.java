package com.lidar.lidar.database;

import javax.persistence.*;
import com.lidar.lidar.samples.*;

@Entity @Table(name = "buoys")
public class Buoy {
    
    public Buoy() {
        
    }
    
    public Buoy(String serial, Mast mast, SpeedHeightTable speedHeights) {
        this.serial = serial;
        this.mast = mast;
        sh40 = new SpeedHeight(40);
        speedHeights.save(sh40);
        sh60 = new SpeedHeight(60);
        speedHeights.save(sh60);
        sh80 = new SpeedHeight(80);
        speedHeights.save(sh80);
        sh100 = new SpeedHeight(100);
        speedHeights.save(sh100);
    }

    @Id @Column(name = "serial")
    String serial;

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @ManyToOne @JoinColumn(name = "mast")
    Mast mast;

    public String getMastSerial() {
        return mast.getSerial();
    }

    @OneToOne @JoinColumn(name = "sh40")
    SpeedHeight sh40;

    public SpeedHeight getSh40() {
        return sh40;
    }
    
    @OneToOne @JoinColumn(name = "sh60")
    SpeedHeight sh60;

    public SpeedHeight getSh60() {
        return sh60;
    }
    
    @OneToOne @JoinColumn(name = "sh80")
    SpeedHeight sh80;

    public SpeedHeight getSh80() {
        return sh80;
    }
    
    @OneToOne @JoinColumn(name = "sh100")
    SpeedHeight sh100;

    public SpeedHeight getSh100() {
        return sh100;
    }

    public void addSamples(BuoySample buoySample, MastSample mastSample) {
        sh40.addSamples(buoySample, mastSample);
        sh60.addSamples(buoySample, mastSample);
        sh80.addSamples(buoySample, mastSample);
        sh100.addSamples(buoySample, mastSample);
    }

    public void saveData(SpeedHeightTable speedHeights) {
        speedHeights.save(sh40);
        speedHeights.save(sh60);
        speedHeights.save(sh80);
        speedHeights.save(sh100);
    }

    public void reset(SpeedHeightTable speedHeights) {
        sh40.reset();
        sh60.reset();
        sh80.reset();
        sh100.reset();
        saveData(speedHeights);
    }
}