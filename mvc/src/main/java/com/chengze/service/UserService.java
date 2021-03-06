package com.chengze.service;

import com.chengze.domain.Authority;
import com.chengze.domain.User;
import com.chengze.repository.UserRepository;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    AuthorityService authorityService;
    @Autowired
    MessageSQSService messageSQSService;


    private final Logger logger = LoggerFactory.getLogger(getClass());

    public List<User> findAll() {
        logger.info("total number of array is:" + userRepository.findAll().size());
        if (userRepository.findAll().size() > 1) {
            return userRepository.findAll();
        } else {
            return new ArrayList<>();
        }
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    @Transactional
    public User createUser(User newUser) {
        newUser.setAccountNonExpired(true);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);
        newUser.setEnabled(true);
        authorityService.addAuthority(newUser, "ROLE_REGISTERED_USER");
        String encodedPass = encoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPass);
        userRepository.save(newUser);
//        messageSQSService.sendMessage("Welcome to Formula!");
        return newUser;
    }


    public User save(User user) {
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }


    @Transactional
    public User findByUsernameIgnoreCase(String username) throws NullPointerException, NotFoundException {

        if (username == null || "".equals(username.trim()))
            throw new NullPointerException("search username is null");
        {
            User user = userRepository.findByUsernameIgnoreCase(username);
            if (user == null) {
                throw new NotFoundException("can not find user");
            }
//        return userRepository.findByUsernameIgnoreCase(username);
            return user;
        }
    }
}
//    public List<Authority> findAuthorities(User user){
//        return userRepository.findAuthorities(user);
//    }

