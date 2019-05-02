package com.chengze.domain;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=SEQUENCE, generator="user_id_seq")
    @SequenceGenerator(name="user_id_seq", sequenceName="user_id_seq", allocationSize=1)
    private Long id;


    @Column(unique=true)
    private String username;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Car> cars;

    public Long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername(){

        return username;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getFirstName(){

        return firstName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getLastName(){

        return lastName;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getEmail(){

        return email;
    }

    public void setPassword(String password) {

        this.password = password;
    }


//    public List<Car> getCars(){
//        return cars;
//    }
}
