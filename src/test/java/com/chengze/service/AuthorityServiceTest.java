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
        userService.save(u);
        a.setUser(u);
        authorityService.save(a);
        List<Authority> testAuthority = authorityService.findByUserId(u.getId());
        assertNotNull(testAuthority);
        //assertEquals(size(2));
        assertEquals(a.getAuthority(), testAuthority.get(0).getAuthority());
    }
}
