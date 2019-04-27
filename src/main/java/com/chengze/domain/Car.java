package com.chengze.domain;

import javax.persistence.*;

import java.util.ArrayList;
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

    //user de get set
    public void setUser(User u) {
        this.user = u;
    }

    public User getUser(){
        return user;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getMake(){

        return make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel(){

        return model;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice(){

        return price;
    }

    public void setYear(String year) {

        this.year = year;
    }

    public String getYear(){

        return year;
    }


    public static void main(String[] args){
        List<Car> cars = new ArrayList<>();
        cars.add(null);
        cars.add(new Car());

        List<Car> cars1 = null;
    }
}

