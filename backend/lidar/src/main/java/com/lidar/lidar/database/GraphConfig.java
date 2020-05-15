package com.lidar.lidar.database;

import javax.persistence.*;

@Entity @Table(name = "graph_configs")
public class GraphConfig {
    public GraphConfig() {

    }

    public GraphConfig(String name, Long pageLength, Long dataLength) {
        this.name = name;
        this.pageLength = pageLength;
        this.dataLength = dataLength;
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

    @Column(name = "name")
    String name;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "page_length")
    Long pageLength;

    /**
     * @return the pageLength
     */
    public Long getPageLength() {
        return pageLength;
    }

    /**
     * @param pageLength the pageLength to set
     */
    public void setPageLength(Long pageLength) {
        this.pageLength = pageLength;
    }

    @Column(name = "data_length")
    Long dataLength;

    /**
     * @return the dataLength
     */
    public Long getDataLength() {
        return dataLength;
    }

    /**
     * @param dataLength the dataLength to set
     */
    public void setDataLength(Long dataLength) {
        this.dataLength = dataLength;
    }
}