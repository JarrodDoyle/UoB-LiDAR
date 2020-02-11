package com.lidar.lidar.database;

import javax.persistence.*;

@Entity @Table(name = "buoys")
public class Buoy {
    public Buoy() {

    }

    public Buoy(String serial, Mast mast) {
        this.serial = serial;
        this.mast = mast;
    }

    @Id @Column(name = "serial")
    String serial;

    @ManyToOne @JoinColumn(name = "mast")
    Mast mast;

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @Column(name = "speeda")
    Double speeda;

    public Double getSpeeda() {
        return speeda;
    }

    public void setSpeeda(Double speeda) {
        this.speeda = speeda;
    }

    @Column(name = "speedb")
    Double speedb;

    public Double getSpeedb() {
        return speedb;
    }

    public void setSpeedb(Double speedb) {
        this.speedb = speedb;
    }

    @Column(name = "mastspeeda")
    Double mastSpeeda;

    public Double getMastSpeeda() {
        return mastSpeeda;
    }

    public void setMastSpeeda(Double mastSpeeda) {
        this.mastSpeeda = mastSpeeda;
    }

    @Column(name = "mastspeedb")
    Double mastSpeedb;

    public Double getMastSpeedb() {
        return mastSpeedb;
    }

    public void setMastSpeedb(Double mastSpeedb) {
        this.mastSpeedb = mastSpeedb;
    }
}
