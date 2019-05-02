package com.chengze.repository;

import com.chengze.domain.Car;
import com.chengze.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findAll();


//    Car car= car.findById();
    List<Car> findByMakeIgnoreCase(String make);

//    @Query("select c FROM Car c LEFT JOIN FETCH c.user where c.user.id = ?1")
//    Optional<Car> findCarsByUserId(Long Id);
}
