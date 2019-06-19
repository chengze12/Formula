package com.chengze.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.UUID;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="images")
public class Image {
        @Id
        @GeneratedValue(strategy =SEQUENCE , generator="image_id_seq")
        @SequenceGenerator(name="image_id_seq", sequenceName= "image_id_seq", allocationSize=1)
        Long Id;
        @Column(name="front")
        private String front;


        @Column(name="back")
        private String back;

        @Column(name="interior")
        private String interior;

        @Column(name="url")
        private String url;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "car_id")
        private Car car;

        @NotNull
        @Column(name="UUID", unique = true)
        private String uuid= UUID.randomUUID().toString();

        @NotNull
        @Column(name = "extention")
        private String extention;


//        private LamStorageService lamStorageService;

        public void setCar(Car c) {
                this.car = c;
        }

        public Car getCar(){
                return car;
        }

        public void setFront(String front) {

                this.front = front;
        }

        public String getFront(){
                return front;
        }

        public void setBack(String back) {

                this.back = back;
        }

        public String getBack(){

                return back;
        }

        public void setInterior(String interior) {

                this.interior = interior;
        }

        public String getInterior(){

                return interior;
        }


        public void seturl(String url){
                this.url=url;
        }
        public String getUrl(){
                return url;
        }

        public String getUuid(){
                return uuid;
        }


        public void setExtention(String extention){
                this.extention=extention;
        }
        public String getExtention(){
                return extention;
        }
}
