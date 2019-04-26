package com.chengze.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="images")
public class Image {
        @Id
        @GeneratedValue(strategy =SEQUENCE , generator="image_id_seq")
        @SequenceGenerator(name="image_id_seq", sequenceName= "image_id_seq", allocationSize=1)

        @Column(name="front")
        private String front;


        @Column(name="back")
        private String back;

        @Column(name="interior")
        private String interior;


        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "car_id")
        private Car car;

        public void setFront(String front) { front = front; }
        public String getFront() { return front ; }

        public void setback(String back) { back = back; }
        public String getBack() { return back ; }

        public void setInterior(String interior) { interior = interior; }
        public String getInterior() { return interior ; }
}