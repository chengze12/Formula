package com.chengze.service;

import com.chengze.config.AppConfig;
import com.chengze.domain.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@WebAppConfiguration
@ContextConfiguration(classes ={AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class CarServiceTest {
    @Autowired
    private CarService carService;

    @Test
    @Transactional
    public void findByMakeTest(){
        Car c= new Car();
        c.setMake("Honda");
        c.setPrice("1000");
        c.setModel("camry");
        c.setYear("2011");
        carService.save(c);
        List<Car> testCar = carService.findByMakeIgnoreCase(c.getMake());
        assertNotNull(testCar);
        //assertEquals(size(2));
        assertEquals(c.getMake(), testCar.get(0).getMake());
    }
}
