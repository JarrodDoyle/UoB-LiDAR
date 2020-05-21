package com.lidar.lidar.database;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuoyTable extends CrudRepository<Buoy, String> {   
    public List<Buoy> findByMast(Mast mast);

    public List<Buoy> deleteByMast(Mast mast);
}
   