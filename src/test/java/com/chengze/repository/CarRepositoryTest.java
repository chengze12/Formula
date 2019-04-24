package com.chengze.repository;

import com.chengze.config.AppConfig;
import com.chengze.domain.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.Optional;

@WebAppConfiguration
@ContextConfiguration(classes ={AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("Unit")
public class CarRepositoryTest {
    @Autowired
//    private CarRepository carRepository;

    @Test
    @Transactional
    public void findByIdTest(){
//        Car c=new Car();
//        c.setBrand("Aucra");
//        c.setModel("SUV");
//        carRepository.save(c);
//        Optional<Car> testCar=carRepository.findById(c.getId());
//        asserBotNull(testCar);
//        assertEquals(c.getId(), testCar.get().getId());
    }
}
