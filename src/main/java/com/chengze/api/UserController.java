package com.chengze.api;

import com.chengze.domain.Authority;
import com.chengze.domain.User;
import com.chengze.extend.security.RestAuthenticaitionRequest;
import com.chengze.repository.UserRepository;
import com.chengze.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@ResponseBody
@RequestMapping(value= {"/api/users" , "/api/user"})
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;
//  http://localhost:8080/api/users
    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUserList() {
        logger.debug("list users");
        return userService.findAll();
//        return null;
    }

//    String admin;
//    private List<Authority> authorities={};
    //http://localhost:8080/api/users
    @RequestMapping(method = RequestMethod.POST)
    public User signUpUser(@RequestBody User user) {
        userService.createUser(user);
//        user.setAuthorities(authorities);
        return user;
    }

    //http://localhost:8080/api/users/1
    @RequestMapping(method = RequestMethod.GET, value = "/{Id}")
    public User getUserById(@PathVariable("Id") Long Id) {
        User getuser = userService.findById(Id);
        return getuser;
    }

    //http://localhost:8080/api/users?username=chengze12 //parameter
    @RequestMapping(method = RequestMethod.GET, params = {"username"})
    public User getUserByUsername(@RequestParam("username") String username) {
        return userService.findByUsernameIgnoreCase(username);
    }

    //http://localhost:8080/api/users/1
    @RequestMapping(method = RequestMethod.DELETE, value = "/{Id}")
    public User deleteUserById(@PathVariable("Id") Long Id){
        User deleteuser = userService.findById(Id);
        return deleteuser;
    }

    //http://localhost:8080/api/users/login
    @RequestMapping(value="/login",method = RequestMethod.POST )
        public RestAuthenticaitionRequest login(@RequestBody RestAuthenticaitionRequest restAuthenticaitionRequest){
        logger.info("123");
        return restAuthenticaitionRequest ;
    }
}
