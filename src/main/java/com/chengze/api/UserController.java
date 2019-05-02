package com.chengze.api;

import com.chengze.domain.User;
import com.chengze.repository.UserRepository;
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
    private UserRepository userRepository;
    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUserList() {
        logger.debug("list users");
       return userRepository.findAll();
//        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public User signUpUser(@RequestBody User user){
//        User user= new User();
        userRepository.save(user);
        return user;
    }
    @RequestMapping(method = RequestMethod.GET,value="/{Id}")
    public User getUserById(@PathVariable("Id")  Long Id){
        Optional<User> opt= userRepository.findById(Id);
        return opt.get();
    }

    @RequestMapping(method = RequestMethod.GET, params = {"username"})
    public User getUserByUsername(@RequestParam("username") String username){
        Optional <User>  user= userRepository.findByUsernameIgnoreCase(username);
        return user.get();
    }
}
