package com.chengze.service;

import com.chengze.domain.User;
import com.chengze.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private List<User> findAll(){
        logger.info("total number of array is:" + userRepository.findAll().size());
        if (userRepository.findAll().size()>1){
            return userRepository.findAll();
        }else{
            return new ArrayList<>();
        }
    }
}
