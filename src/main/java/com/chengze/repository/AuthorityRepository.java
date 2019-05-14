package com.chengze.repository;

import com.chengze.domain.Authority;
import com.chengze.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {
//    List<Authority> findAll();

   //List<Authority> findByUser_Id(Long Id);
    @Query("select a FROM Authority a where a.user.id = ?1")
    List<Authority> findAuthorityByUserId(Long Id);

//    List<Authority> findAuthorities(User user);
}
