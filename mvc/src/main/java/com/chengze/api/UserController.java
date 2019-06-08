package com.chengze.api;

import com.chengze.domain.User;
import com.chengze.extend.security.JwtTokenUtil;
import com.chengze.extend.security.RestAuthenticaitionRequest;
import com.chengze.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Controller
@ResponseBody
@RequestMapping(value= {"/api/users" , "/api/user"})
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


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
        User user=null;
        try{
            user= userService.findByUsernameIgnoreCase(username);
        }
        catch (NotFoundException reportproblem){
            logger.debug("some probelm found");
        }
        return user;
    }

    //http://localhost:8080/api/users/1
    @RequestMapping(method = RequestMethod.DELETE, value = "/{Id}")
    public User deleteUserById(@PathVariable("Id") Long Id) {
        User deleteuser = userService.findById(Id);
        return deleteuser;
    }



    //http://localhost:8080/api/users/login

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map> login(@RequestBody RestAuthenticaitionRequest authenticaitionRequest) {
        try {
            Authentication notFullyAuthenticated = new UsernamePasswordAuthenticationToken(

                    authenticaitionRequest.getUsername(),
                    authenticaitionRequest.getPassword()
            );
            final Authentication authentication = authenticationManager.authenticate(notFullyAuthenticated);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            try{
                final UserDetails userDetails=userService.findByUsernameIgnoreCase((authenticaitionRequest.getUsername()));
                final String token=jwtTokenUtil.generateToken(userDetails);
                return ResponseEntity.ok(jwtTokenUtil.mapToken(token));
            }catch (NotFoundException e){
                logger.error("System cant find user by email or username");
                return ResponseEntity.notFound().build();
            }
//            return ResponseEntity.ok("login successful");
        } catch (AuthenticationException ex) {
            logger.error("authentication failure");
            return  null;
 //  todo: return map          ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("authentication failure, check your username");
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @JsonView(User.WithoutPasswordView.class)
    public User getUser() {
        return new User();
    }
}
