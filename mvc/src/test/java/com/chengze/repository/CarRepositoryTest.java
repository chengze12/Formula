package com.chengze.repository;

import com.chengze.domain.Car;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Optional;

public class CarRepositoryTest {
    @Autowired
    private CarRepository carRepository;

    @Test
    @Transactional
    public void findByIdTest() {
        Car c = new Car();
        c.setMake("honda");
        carRepository.save(c);
       // Optional<Car> testCar=carRepository.findCarsByUserId(c.getId( ));

//        c.setUsername("chengze");
//        userRepository.save(c);
//        Optional<User> testUser=userRepository.findById(c.getId());
////to test if the thing u get is null
//        assertNotNull(testUser);
////to jarge if the test u get and the c u save is same
//        assertEquals(c.getId(),testUser.get().getId());
    }
}
