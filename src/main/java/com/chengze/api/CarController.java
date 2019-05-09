package com.chengze.api;

import com.chengze.domain.Car;
import com.chengze.domain.User;
import com.chengze.repository.CarRepository;
import com.chengze.service.CarService;
import com.chengze.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;


@Controller
@ResponseBody
@RequestMapping(value= {"/api/cars" , "/api/car"})
public class CarController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;
    //
    @RequestMapping(method = RequestMethod.GET)
    public List<Car> getCarList() {
        logger.debug("list cars");
        return carService.findAll();
//        return null;
    }

    //http://localhost:8080/api/cars/user/1
    @RequestMapping(value = "/user/{user_id}",method = RequestMethod.POST)
    public Car UploadCar(@RequestBody Car car , @PathVariable("user_id") Long userId) {
//        Car car= new Car();
        User user = userService.findById(userId);
        car.setUser(user);
        return carService.save(car);
    }

    //http://localhost:8080/api/cars/1
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Car getCarById(@PathVariable("id") Long Id) {
        Car opt = carService.findById(Id);
        return opt;
    }

    @RequestMapping(method = RequestMethod.GET, params = {"make"})
    public List<Car> getCarByMake(@RequestParam("make") String make) {
        return carService.findByMakeIgnoreCase(make);
    }

    //http://localhost:8080/api/users?model=SUV
    @RequestMapping(method = RequestMethod.GET, params = {"model"})
    public List<Car> getCarByModel(@RequestParam("model") String model) {
        return carService.findByModelIgnoreCase(model);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public Car deleteCarById(@PathVariable("id") Long Id){
        Car delete = carService.findById(Id);
        return delete;

    }
}
