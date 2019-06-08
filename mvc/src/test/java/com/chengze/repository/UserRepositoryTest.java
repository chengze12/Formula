package com.chengze.repository;

import com.chengze.config.AppConfig;
import com.chengze.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@WebAppConfiguration
@ContextConfiguration(classes ={AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("Unit")
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Test
    @Transactional
    public void findByIdTest(){
        User c= new User();
        c.setUsername("chengze");
        userRepository.save(c);
        Optional<User> testUser=userRepository.findById(c.getId());
        //to test if the thing u get is null
        assertNotNull(testUser);
        //to jarge if the test u get and the c u save is same
        assertEquals(c.getId(),testUser.get().getId());
    }
}
