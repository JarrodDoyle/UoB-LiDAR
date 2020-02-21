package dev.icedcoffee.lidar.Login;

import org.springframework.data.repository.CrudRepository;

public interface LoginTable extends CrudRepository<Login, String> { 
    public Optional<Login> findByEmail(String email);
}