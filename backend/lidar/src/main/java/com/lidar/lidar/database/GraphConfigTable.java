package com.lidar.lidar.database;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GraphConfigTable extends CrudRepository<GraphConfig, Long> {
    public List<GraphConfig> findByName(String name);
}
