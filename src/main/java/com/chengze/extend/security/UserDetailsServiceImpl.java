package com.chengze.extend.security;

import com.chengze.domain.Authority;
import com.chengze.domain.User;
import com.chengze.service.AuthorityService;
import com.chengze.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

@Autowired
    private UserService userService;
@Autowired
    private AuthorityService authorityService;

    private  final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String emailorUsername) throws UsernameNotFoundException {

        logger.debug(emailorUsername+"is try to log in from spring security");
        User domainUser= userService.findByUsernameIgnoreCase(emailorUsername);
        List<Authority> userAuthorities = authorityService.findAuthority(domainUser);
        domainUser.setAuthorities(userAuthorities);
        return domainUser;
    }

}
