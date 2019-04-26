package com.chengze.repository;

import com.chengze.domain.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findAll();

//    @Query("select c FROM #{#entityName} c LEFT JOIN FETCH c.images")
//    List<Car> findByIdWithImages();
//
//    @Query("select c FROM Car  c LEFT JOIN FETCH c.images where c.id = ?1")
//    Optional<Car> findByIdWithImages(Long Id);
}
