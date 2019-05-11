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

    List<Car> findByModelIgnoreCase(String model);

//    @Query("select u FROM User u LEFT JOIN FETCH u.car where c.id = ?1")
//    Optional<User> findCarsByUserId(Long Id);
}
