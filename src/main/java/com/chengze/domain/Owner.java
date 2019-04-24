package com.chengze.domain;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="owner")
public class Owner {
    @Id
    @GeneratedValue(strategy=SEQUENCE, generator="owner_id_seq")
    @SequenceGenerator(name="owner_id_seq", sequenceName="owner_id_seq", allocationSize=1)
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Car> car;

    public void setUsername(String user_name) {
        username = user_name;
    }

//    public void setEmail(String email) {
//        email = email;
//    }
    public Long getId() {
        return id;
    }
}
