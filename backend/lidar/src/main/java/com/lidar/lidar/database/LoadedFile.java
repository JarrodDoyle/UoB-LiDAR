package com.lidar.lidar.database;

import javax.persistence.*;

@Entity @Table(name = "loaded_files")
public class LoadedFile {
    @Id @GeneratedValue @Column(name = "id")
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LoadedFile() {

    }
    
    public LoadedFile(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id.toString() + ", " + name;
    }
}