package com.lidar.lidar.database;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuoyGraphTable extends CrudRepository<BuoyGraph, Long> {
    public List<BuoyGraph> findByBuoy(Buoy buoy);
    public List<BuoyGraph> deleteByBuoy(Buoy buoy);
}
