package com.chengze.service;

import com.chengze.domain.Authority;
import com.chengze.repository.AuthorityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityService {
    @Autowired
    public AuthorityRepository authorityRepository;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Authority save(Authority role){
        return authorityRepository.save(role);
    }
    public List<Authority> findByUserId(Long Id ){
        return authorityRepository.findAuthorityByUserId(Id);
    }
}
