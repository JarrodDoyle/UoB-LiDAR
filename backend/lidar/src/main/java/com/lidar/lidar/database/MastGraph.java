package com.lidar.lidar.database;

import javax.persistence.*;

@Entity @Table(name = "mast_graphs")
public class MastGraph {
    public MastGraph() {

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

    @Column(name = "min")
    Double min;

    /**
     * @return the min
     */
    public Double getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(Double min) {
        this.min = min;
    }

    @Column(name = "max")
    Double max;

    /**
     * @return the max
     */
    public Double getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(Double max) {
        this.max = max;
    }

    @Column(name = "sum")
    Double sum;

    /**
     * @return the sum
     */
    public Double getSum() {
        return sum;
    }

    /**
     * @param sum the sum to set
     */
    public void setSum(Double sum) {
        this.sum = sum;
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
    Long start;

    /**
     * @return the start
     */
    public Long getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(Long start) {
        this.start = start;
    }

    @ManyToOne @JoinColumn(name = "mast")
    Mast mast;

    /**
     * @return the mast
     */
    public Mast getMast() {
        return mast;
    }

    /**
     * @param mast the mast to set
     */
    public void setMast(Mast mast) {
        this.mast = mast;
    }
}