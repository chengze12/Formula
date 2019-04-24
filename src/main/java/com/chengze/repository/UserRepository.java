//package com.chengze.repository;
//
//import com.chengze.domain.Car;
//import com.chengze.domain.Owner;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface UserRepository extends CrudRepository<Owner, Long > {
//    List<Owner> findAll();
//
//    @Query("Select c FROM #{#entityName} c LEFT JOIN c.cars")
//    List<Car> findAllWithImages();
//
//    Car save();
//
//    @Query("Select c FROM #{#entityName} c LEFT JOIN c.cars where c.id= ?1")
//    Optional<Owner> findByIdWithCars(Long Id);
//}
