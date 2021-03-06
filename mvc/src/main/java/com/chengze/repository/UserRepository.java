package com.chengze.repository;

import com.chengze.domain.Authority;
import com.chengze.domain.User;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long > {
        List<User> findAll();

//        @Query(Select)
       User findByUsernameIgnoreCase(String username);

//    @Query("select u FROM User u LEFT JOIN FETCH u.car where c.id = ?1")
//    Optional<User> findCarsByUserId(Long Id);



}
