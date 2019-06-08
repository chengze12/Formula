package com.chengze.repository;

import com.chengze.domain.Car;
import com.chengze.domain.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image, Long> {
    List<Image> findAll();

    List<Car> findByCarId(String model);
}
