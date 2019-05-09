package com.chengze.api;

import com.chengze.domain.Car;
import com.chengze.domain.Image;
import com.chengze.domain.User;
import com.chengze.service.CarService;
import com.chengze.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@ResponseBody
@RequestMapping(value= {"/api/images" , "/api/image"})
public class ImageController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ImageService imageService;

    @Autowired
    private CarService carService;

    //http://localhost:8080/api/images
    @RequestMapping(method = RequestMethod.POST)
    public Image postImage(@RequestBody Image image) {
        imageService.save(image);
        return image;
    }

    //http://localhost:8080/api/images/car/1
    @RequestMapping(value = "/car/{car_id}",method = RequestMethod.POST)
    public Image UploadImage(@RequestBody Image image , @PathVariable("car_id") Long carId) {
//        Car car= new Car();
        Car car = carService.findById(carId);
        image.setCar(car);
        return imageService.save(image);
    }
    //http://localhost:8080/api/images/1
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public Image deleteImageById(@PathVariable("id") Long Id){
        Image delete = imageService.findById(Id);
        return delete;

    }
}
