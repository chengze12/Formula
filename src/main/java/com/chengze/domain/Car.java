package com.chengze.domain;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="cars")
public class Car {
    @Id
    @GeneratedValue(strategy =SEQUENCE , generator="car_id_seq")
    @SequenceGenerator(name="car_id_seq", sequenceName= "car_id_seq", allocationSize=1)
    private Long id;

    @Column(name="make")
    private String make;

    @Column(name="model")
    private String model;

    @Column(name="price")
    private String price;

    @Column(name="year")
    private String year;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car", cascade = CascadeType.ALL)
    private List<Image> image;


}

