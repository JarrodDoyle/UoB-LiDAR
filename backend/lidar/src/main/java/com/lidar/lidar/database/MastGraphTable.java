package com.lidar.lidar.database;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MastGraphTable extends CrudRepository<MastGraph, Long> {
    public List<MastGraph> findByMast(Mast mast);
}
