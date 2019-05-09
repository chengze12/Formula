package com.chengze.service;

import com.chengze.domain.User;
import com.chengze.repository.UserRepository;
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
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public List<User> findAll(){
        logger.info("total number of array is:" + userRepository.findAll().size());
        if (userRepository.findAll().size()>1){
            return userRepository.findAll();
        }else{
            return new ArrayList<>();
        }
    }
    private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();

    @Transactional
    public User createUser(User newUser){
        String encodedPass = encoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPass);
        save(newUser);
        return newUser;
    }


    public User save(User user){
        return userRepository.save(user);
    }

    public User findById(Long id){
        return userRepository.findById(id).get();
    }

    public User findByUsernameIgnoreCase(String username ){
        return userRepository.findByUsernameIgnoreCase(username);
    }
}
