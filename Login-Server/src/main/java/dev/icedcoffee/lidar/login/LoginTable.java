package dev.icedcoffee.lidar.login;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface LoginTable extends CrudRepository<Login, String> { 
    public List<Login> findByEmail(String email);
}