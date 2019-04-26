package com.chengze.repository;

import com.chengze.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long > {
    List<User> findAll();

    @Query("Select c FROM #{#entityName} c LEFT JOIN FETCH c.car")
    List<User> findAllWithCar();

    @Query("Select c FROM Owner c LEFT JOIN FETCH c.car where c.id= ?1")
    Optional<User> findByIdWithCar(Long Id);
}
