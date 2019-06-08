package com.chengze.extend;

import com.chengze.config.AppConfig;
import com.chengze.domain.Authority;
import com.chengze.domain.User;
import com.chengze.extend.security.UserDetailsServiceImpl;
import com.chengze.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;


@WebAppConfiguration
@ContextConfiguration(classes ={AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class UserDetailsServiceImplTest {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void loadUserByUsernametest(){
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
        Authority authority = new Authority();
        authority.setRole("admin");
        authority.setUser(u);
        userRepository.save(u);
        UserDetails testUser = userDetailsService.loadUserByUsername(u.getUsername());
        assertNotNull (testUser);
        assertEquals(u.getUsername(),testUser.getUsername());
        assertEquals(u.getAuthorities(),testUser.getAuthorities());
    }
}
