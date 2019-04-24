package com.chengze.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="car")
public class Car {
    @Id
    @GeneratedValue(strategy =SEQUENCE , generator="car_id_seq")
    @SequenceGenerator(name="car_id_seq", sequenceName= "car_id_seq", allocationSize=1)

    @Column(name="make")
    private String make;

    @Column(name="model")
    private String model;

    @Column(name="price")
    private String price;

    @Column(name="location")
    private String location;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;
}

