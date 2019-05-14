package com.chengze.service;

import com.chengze.config.AppConfig;
import com.chengze.domain.Authority;
import com.chengze.domain.User;
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
public class AuthorityServiceTest {
    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private UserService userService;

    @Test
    @Transactional
    public void findByUserIdTest() {
        Authority a = new Authority();
        a.setRole("admin");
        User u = new User();
        u.setUsername("pangzi");
        u.setFirstName("pang");
        u.setLastName("zi");
        u.setEmail("ewq@gmail.com");
        u.setPassword("123");
        u.setAccountNonExpired(true);
        u.setAccountNonLocked(true);
        u.setCredentialsNonExpired(true);
        u.setEnabled(true);
//        u.isCredentialsNonExpired();
//        u.setAccountNonExpired(true);
        userService.save(u);
        a.setUser(u);
        authorityService.save(a);
        List<Authority> testAuthority = authorityService.findAuthority(u);
        assertNotNull(testAuthority);
        //assertEquals(size(2));
        assertEquals(a.getAuthority(), testAuthority.get(0).getAuthority());
    }

    @Test
    @Transactional
    public void createUserWithAuthority(){
        Authority authority = new Authority();
        authority.setRole("admin");
//        UserService userService= new UserService();
//        userService.createUser()
        User user=new User();
        user.setUsername("pangzi");
        user.setFirstName("pang");
        user.setLastName("zi");
        user.setEmail("ewq@gmail.com");
        user.setPassword("123");
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        authority.setUser(user);
        userService.save(user);
        authorityService.save(authority);
        List<Authority> testAuthority = authorityService.findAuthority(user);
        assertNotNull(testAuthority);
        assertEquals(authority.getAuthority(), testAuthority.get(0).getAuthority());
    }
}
