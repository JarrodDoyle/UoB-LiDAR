package com.lidar.lidar.database;

import javax.persistence.*;

@Entity @Table(name = "masts")
public class Mast {
    public Mast() {

    }

    public Mast(String serial) {
        this.serial = serial;
    }

    @Id @Column(name = "serial")
    String serial;

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}
