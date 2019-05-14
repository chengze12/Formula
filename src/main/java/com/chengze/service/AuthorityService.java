package com.chengze.service;

import com.chengze.domain.Authority;
import com.chengze.domain.User;
import com.chengze.repository.AuthorityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class AuthorityService {
    @Autowired
    public AuthorityRepository authorityRepository;
    private Authority roles;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Authority save(Authority role){
        return authorityRepository.save(role);
    }

    @Transactional
    public Authority addAuthority(User user, String authoriryString){
        Authority authority = new Authority();
        authority.setRole(authoriryString);
        authority.setUser(user);
        return authorityRepository.save(authority);
    }

    @Transactional
    public List<Authority> findAuthority(User user ){
        List<Authority> roles= authorityRepository.findAuthorityByUserId(user.getId());
        return roles;
    }
}
