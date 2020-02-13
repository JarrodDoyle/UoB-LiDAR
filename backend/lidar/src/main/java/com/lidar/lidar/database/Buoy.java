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

    @OneToOne @JoinColumn(name = "sh60")
    SpeedHeight sh60;

    @OneToOne @JoinColumn(name = "sh80")
    SpeedHeight sh80;

    @OneToOne @JoinColumn(name = "sh100")
    SpeedHeight sh100;

    public void addSamples(BuoySample buoySample, MastSample mastSample) {
        sh40.addSamples(buoySample, mastSample);
        sh60.addSamples(buoySample, mastSample);
        sh80.addSamples(buoySample, mastSample);
        sh100.addSamples(buoySample, mastSample);
    }

    public Double xmwsa40() {
        return sh40.slopea();
    }

    public Double xmwsb40() {
        return sh40.slopeb();
    }
}