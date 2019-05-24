package com.chengze.service;

import com.chengze.config.AppConfig;
import com.chengze.domain.User;
import com.chengze.extend.security.JwtTokenUtil;
import com.sun.tools.corba.se.idl.constExpr.Not;
import javassist.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import java.util.Date;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@WebAppConfiguration
@ContextConfiguration(classes ={AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class JwtTokenTest {

    @Autowired
     private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private  UserService userService;

    @Test
    @Transactional
    public void TokenTest() throws NotFoundException {
        User u= new User();
        u.setUsername("pang");
        u.setFirstName("pang");
        u.setLastName("zi");
        u.setPassword("1234567");
        u.setEmail("chengze1234@gmail.com");
        u.setAccountNonExpired(true);
        u.setAccountNonLocked(true);
        u.setCredentialsNonExpired(true);
        u.setEnabled(true);
        userService.save(u);
//        User testUser = userService.findByUsernameIgnoreCase(u.getUsername());
        String token1=jwtTokenUtil.generateToken(u);
        String usernametest= jwtTokenUtil.getUsernameFromToken(token1);
        assertNotNull(usernametest);
        assertEquals(u.getUsername(),usernametest);

//        Date IsExpiretest= jwtTokenUtil.getExpirationDateFromToken(token1);
//        assertNotNull(IsExpiretest);
    }

    private User u;

    public void generateTokenTest() throws NotFoundException{
        String token= jwtTokenUtil.generateToken(u);
        String[] test = token.split("\\.");
        assertEquals(3,test.length);
    }
}
