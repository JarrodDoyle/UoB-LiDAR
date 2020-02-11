package com.lidar.lidar.database;

import javax.persistence.*;

@Entity @Table(name = "buoy_samples")
public class BuoySample {
    @Id @GeneratedValue @Column(name = "id")
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne @JoinColumn(name = "serial")
    Buoy buoy;

    public Buoy getBuoy() {
        return buoy;
    }

    public void setBuoy(Buoy buoy) {
        this.buoy = buoy;
    }

    @Column(name = "timestamp")
    String timestamp;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Column(name = "direction30m")
    Double direction30m;

    public Double getDirection30m() {
        return direction30m;
    }

    public void setDirection30m(Double direction30m) {
        this.direction30m = direction30m;
    }

    @Column(name = "direction40m")
    Double direction40m;

    public Double getDirection40m() {
        return direction40m;
    }

    public void setDirection40m(Double direction40m) {
        this.direction40m = direction40m;
    }

    @Column(name = "direction60m")
    Double direction60m;

    public Double getDirection60m() {
        return direction60m;
    }

    public void setDirection60m(Double direction60m) {
        this.direction60m = direction60m;
    }

    @Column(name = "direction80m")
    Double direction80m;

    public Double getDirection80m() {
        return direction80m;
    }

    public void setDirection80m(Double direction80m) {
        this.direction80m = direction80m;
    }

    @Column(name = "direction100m")
    Double direction100m;

    public Double getDirection100m() {
        return direction100m;
    }

    public void setDirection100m(Double direction100m) {
        this.direction100m = direction100m;
    }
    
    @Column(name = "speed30m")
    Double speed30m;

    public Double getSpeed30m() {
        return speed30m;
    }

    public void setSpeed30m(Double speed30m) {
        this.speed30m = speed30m;
    }
    
    @Column(name = "speed40m")
    Double speed40m;

    public Double getSpeed40m() {
        return speed40m;
    }

    public void setSpeed40m(Double speed40m) {
        this.speed40m = speed40m;
    }

    @Column(name = "speed60m")
    Double speed60m;

    public Double getSpeed60m() {
        return speed60m;
    }

    public void setSpeed60m(Double speed60m) {
        this.speed60m = speed60m;
    }

    @Column(name = "speed80m")
    Double speed80m;

    public Double getSpeed80m() {
        return speed80m;
    }

    public void setSpeed80m(Double speed80m) {
        this.speed80m = speed80m;
    }

    @Column(name = "speed100m")
    Double speed100m;

    public Double getSpeed100m() {
        return speed100m;
    }

    public void setSpeed100m(Double speed100m) {
        this.speed100m = speed100m;
    }
    
    @Column(name = "ti30m")
    Double ti30m;

    public Double getTi30m() {
        return ti30m;
    }

    public void setTi30m(Double ti30m) {
        this.ti30m = ti30m;
    }

    @Column(name = "ti40m")
    Double ti40m;

    public Double getTi40m() {
        return ti40m;
    }

    public void setTi40m(Double ti40m) {
        this.ti40m = ti40m;
    }

    @Column(name = "ti60m")
    Double ti60m;

    public Double getTi60m() {
        return ti60m;
    }

    public void setTi60m(Double ti60m) {
        this.ti60m = ti60m;
    }

    @Column(name = "ti80m")
    Double ti80m;

    public Double getTi80m() {
        return ti80m;
    }

    public void setTi80m(Double ti80m) {
        this.ti80m = ti80m;
    }

    @Column(name = "ti100m")
    Double ti100m;

    public Double getTi100m() {
        return ti100m;
    }

    public void setTi100m(Double ti100m) {
        this.ti100m = ti100m;
    }


    public BuoySample() {
        direction30m = 0d;
        direction40m = 0d;
        direction60m = 0d;
        direction80m = 0d;
        direction100m = 0d;

        speed30m = 0d;
        speed40m = 0d;
        speed60m = 0d;
        speed80m = 0d;
        speed100m = 0d;

        ti30m = 0d;
        ti40m = 0d;
        ti60m = 0d;
        ti80m = 0d;
        ti100m = 0d;
    }
}
