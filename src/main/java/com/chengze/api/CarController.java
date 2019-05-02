package com.chengze.api;

import com.chengze.domain.Car;
import com.chengze.repository.CarRepository;
import com.chengze.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

public class CarController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CarService carService;

    //
    @RequestMapping(method = RequestMethod.GET)
    public List<Car> getCarList() {
        logger.debug("list cars");
        return carService.findAll();
//        return null;
    }
    @RequestMapping(method = RequestMethod.POST)
    public Car UploadCar(@RequestBody Car car) {
//        Car car= new Car();
        return carService.save(car);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Car getCarById(@PathVariable("id") Long Id) {
        Car opt = carService.findById(Id);
        return opt;
    }

    @RequestMapping(method = RequestMethod.GET, params = {"make"})
    public List<Car> getCarByMake(@RequestParam("make") String make) {
        return carService.findByMakeIgnoreCase(make);
    }
}
