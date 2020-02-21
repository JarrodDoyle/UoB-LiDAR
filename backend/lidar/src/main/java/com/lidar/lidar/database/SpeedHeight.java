package com.lidar.lidar.database;

import javax.persistence.*;
import com.lidar.lidar.samples.*;
import com.lidar.lidar.Constants;

@Entity @Table(name = "speedheights")
public class SpeedHeight {
    public SpeedHeight(Integer height) {
        this.height = height;
        speeda = 0.0;
        speedb = 0.0;
        mastSpeeda = 0.0;
        mastSpeedb = 0.0;   
        speedaSqr = 0.0;
        speedbSqr = 0.0;
        mastSpeedaSqr = 0.0;
        mastSpeedbSqr = 0.0;
        speedaProd = 0.0;
        speedbProd = 0.0;
        counta = 0l;
        countb = 0l;
    }

    @Id @GeneratedValue @Column(name = "id")
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "height")
    Integer height;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
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

    @Column(name = "speedasqr")
    Double speedaSqr;

    public Double getSpeedaSqr() {
        return speedaSqr;
    }

    public void setSpeedaSqr(Double speedaSqr) {
        this.speedaSqr = speedaSqr;
    }

    @Column(name = "speedbsqr")
    Double speedbSqr;

    public Double getSpeedbSqr() {
        return speedbSqr;
    }

    public void setSpeedbSqr(Double speedbSqr) {
        this.speedbSqr = speedbSqr;
    }

    @Column(name = "mastspeedasqr")
    Double mastSpeedaSqr;

    public Double getMastSpeedaSqr() {
        return mastSpeedaSqr;
    }

    public void setMastSpeedaSqr(Double mastSpeedaSqr) {
        this.mastSpeedaSqr = mastSpeedaSqr;
    }

    @Column(name = "mastspeedbsqr")
    Double mastSpeedbSqr;

    public Double getMastSpeedbSqr() {
        return mastSpeedbSqr;
    }

    public void setMastSpeedbSqr(Double mastSpeedbSqr) {
        this.mastSpeedbSqr = mastSpeedbSqr;
    }

    @Column(name = "speedaprod")
    Double speedaProd;

    public Double getSpeedaProd() {
        return speedaProd;
    }

    public void setSpeedaProd(Double speedaProd) {
        this.speedaProd = speedaProd;
    }
    
    @Column(name = "speedbprod")
    Double speedbProd;

    public Double getSpeedbProd() {
        return speedbProd;
    }

    public void setSpeedbProd(Double speedbProd) {
        this.speedbProd = speedbProd;
    }

    @Column(name = "counta")
    Long counta;

    public Long getCounta() {
        return counta;
    }

    public void setCounta(Long counta) {
        this.counta = counta;
    }

    @Column(name = "countb")
    Long countb;

    public Long getCountb() {
        return countb;
    }

    public void setCountb(Long countb) {
        this.countb = countb;
    }

    public void addSamples(BuoySample buoySample, MastSample mastSample) {
        Double buoyVal = buoySample.getSpeeds().get(getBuoyIndex());
        Double mastVal = mastSample.getSpeeds().get(getMastIndex());

        Boolean a = buoyVal >= Constants.aMin && buoyVal <= Constants.aMax;
        Boolean b = buoyVal >= Constants.bMin;

        if (a) {
            speeda += buoyVal;
            mastSpeeda += mastVal;
            speedaSqr += (buoyVal * buoyVal);
            mastSpeedaSqr += (mastVal * mastVal);
            speedaProd += (buoyVal * mastVal);
            counta++;
        }

        if (b) {
            speedb += buoyVal;
            mastSpeedb += mastVal;
            speedbSqr += (buoyVal * buoyVal);
            mastSpeedbSqr += (mastVal * mastVal);
            speedbProd += (buoyVal * mastVal);
            countb++;
        }
    }

    private Integer getBuoyIndex() {
        if (height.equals(40)) return 2;
        else if (height.equals(60)) return 4;
        else if (height.equals(80)) return 5;
        else if (height.equals(100)) return 6;
        else return -1;
    }

    private Integer getMastIndex() {
        if (height.equals(40)) return 1;
        else if (height.equals(60)) return 2;
        else if (height.equals(80)) return 3;
        else if (height.equals(100)) return 4;
        else return -1;
    }

    public Double slopea() {
        return speedaProd / speedaSqr;
    }

    public Double rSquareda() {
        Double sqrProd = (counta * speedaProd - speeda * mastSpeeda) * (counta * speedaProd - speeda * mastSpeeda);
        Double prodSqr = (counta * speedaSqr - speeda * speeda) * (counta * mastSpeedaSqr - mastSpeeda * mastSpeeda);
        return sqrProd / prodSqr;
    }

    public Double slopeb() {
        return speedbProd / speedbSqr;
    }

    public Double rSquaredb() {
        Double sqrProd = (countb * speedbProd - speedb * mastSpeedb) * (countb * speedbProd - speedb * mastSpeedb);
        Double prodSqr = (countb * speedbSqr - speedb * speedb) * (countb * mastSpeedbSqr - mastSpeedb * mastSpeedb);
        return sqrProd / prodSqr;
    }
}