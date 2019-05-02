package com.chengze.service;

import com.chengze.domain.Car;
import com.chengze.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    public CarRepository carRepository;
    private final Logger logger = LoggerFactory.getLogger(getClass());


    public Car save(Car car){
        return carRepository.save(car);
    }

    public Car findById(Long id){
        return carRepository.findById(id).get();
    }

    public List<Car> findAll(){
        logger.info("total number of array is:" + carRepository.findAll().size());
        return carRepository.findAll();
    }

    public List<Car> findByMakeIgnoreCase(String make ){
        return carRepository.findByMakeIgnoreCase(make);
    }
}
