package com.lidar.lidar.database;

import javax.persistence.*;
import com.lidar.lidar.samples.*;
import com.lidar.lidar.Constants;

@Entity @Table(name = "dirheights")
public class DirHeight {
    public DirHeight() {
        
    }

    public DirHeight(Integer height) {
        this.height = height;
        dira = 0.0;
        dirb = 0.0;
        mastDira = 0.0;
        mastDirb = 0.0;   
        diraSqr = 0.0;
        dirbSqr = 0.0;
        mastDiraSqr = 0.0;
        mastDirbSqr = 0.0;
        diraProd = 0.0;
        dirbProd = 0.0;
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

    @Column(name = "dira")
    Double dira;

    public Double getDira() {
        return dira;
    }

    public void setDira(Double dira) {
        this.dira = dira;
    }

    @Column(name = "dirb")
    Double dirb;

    public Double getDirb() {
        return dirb;
    }

    public void setDirb(Double dirb) {
        this.dirb = dirb;
    }

    @Column(name = "mastdira")
    Double mastDira;

    public Double getMastDira() {
        return mastDira;
    }

    public void setMastDira(Double mastDira) {
        this.mastDira = mastDira;
    }

    @Column(name = "mastdirb")
    Double mastDirb;

    public Double getMastDirb() {
        return mastDirb;
    }

    public void setMastDirb(Double mastDirb) {
        this.mastDirb = mastDirb;
    }

    @Column(name = "dirasqr")
    Double diraSqr;

    public Double getDiraSqr() {
        return diraSqr;
    }

    public void setDiraSqr(Double diraSqr) {
        this.diraSqr = diraSqr;
    }

    @Column(name = "dirbsqr")
    Double dirbSqr;

    public Double getDirbSqr() {
        return dirbSqr;
    }

    public void setDirbSqr(Double dirbSqr) {
        this.dirbSqr = dirbSqr;
    }

    @Column(name = "mastdirasqr")
    Double mastDiraSqr;

    public Double getMastDiraSqr() {
        return mastDiraSqr;
    }

    public void setMastDiraSqr(Double mastDiraSqr) {
        this.mastDiraSqr = mastDiraSqr;
    }

    @Column(name = "mastdirbsqr")
    Double mastDirbSqr;

    public Double getMastDirbSqr() {
        return mastDirbSqr;
    }

    public void setMastDirbSqr(Double mastDirbSqr) {
        this.mastDirbSqr = mastDirbSqr;
    }

    @Column(name = "diraprod")
    Double diraProd;

    public Double getDiraProd() {
        return diraProd;
    }

    public void setDiraProd(Double diraProd) {
        this.diraProd = diraProd;
    }
    
    @Column(name = "dirbprod")
    Double dirbProd;

    public Double getDirbProd() {
        return dirbProd;
    }

    public void setDirbProd(Double dirbProd) {
        this.dirbProd = dirbProd;
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
        Double buoyVal = buoySample.getDirections().get(getBuoyIndex());
        Double mastVal = mastSample.getDirections().get(getMastIndex());

        Boolean b = buoyVal >= Constants.dirbMin;

        dira += buoyVal;
        mastDira += mastVal;
        diraSqr += (buoyVal * buoyVal);
        mastDiraSqr += (mastVal * mastVal);
        diraProd += (buoyVal * mastVal);
        counta++;

        if (b) {
            dirb += buoyVal;
            mastDirb += mastVal;
            dirbSqr += (buoyVal * buoyVal);
            mastDirbSqr += (mastVal * mastVal);
            dirbProd += (buoyVal * mastVal);
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

    public void reset() {
        dira = 0.0;
        dirb = 0.0;
        mastDira = 0.0;
        mastDirb = 0.0;   
        diraSqr = 0.0;
        dirbSqr = 0.0;
        mastDiraSqr = 0.0;
        mastDirbSqr = 0.0;
        diraProd = 0.0;
        dirbProd = 0.0;
        counta = 0l;
        countb = 0l;
    }

    public Double slopea() {
        Double prod = counta * diraProd - dira * mastDira;
        Double sqr = counta * diraSqr - dira * dira;
        return prod / sqr;
    }

    public Double intercepta() {
        return (mastDira - slopea() * dira) / counta;
    }

    public Double rSquareda() {
        Double sqrProd = (counta * diraProd - dira * mastDira) * (counta * diraProd - dira * mastDira);
        Double prodSqr = (counta * diraSqr - dira * dira) * (counta * mastDiraSqr - mastDira * mastDira);
        return sqrProd / prodSqr;
    }

    public Double slopeb() {
        Double prod = countb * dirbProd - dirb * mastDirb;
        Double sqr = countb * dirbSqr - dirb * dirb;
        return prod / sqr;
    }

    public Double interceptb() {
        return (mastDirb - slopeb() * dirb) / countb;
    }

    public Double rSquaredb() {
        Double sqrProd = (countb * dirbProd - dirb * mastDirb) * (countb * dirbProd - dirb * mastDirb);
        Double prodSqr = (countb * dirbSqr - dirb * dirb) * (countb * mastDirbSqr - mastDirb * mastDirb);
        return sqrProd / prodSqr;
    }
}