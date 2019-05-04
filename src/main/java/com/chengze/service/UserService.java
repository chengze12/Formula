package com.chengze.service;

import com.chengze.domain.User;
import com.chengze.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
