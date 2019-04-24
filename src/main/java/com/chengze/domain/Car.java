package com.chengze.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

public class Car {
    @Id
    @GeneratedValue(strategy =SEQUENCE , generator="cars_id_seq")
    @SequenceGenerator(name="cars_id_seq", sequenceName= "cars_id_seq", allocationSize=1)

    @Column(name="make")
    private String make;

    @Column(name="model")
    private String model;

    @Column(name="price")
    private String price;

    @Column(name="location")
    private String location;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}

