package com.chengze.domain;

import javax.persistence.*;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy =SEQUENCE , generator="users_id_seq")
    @SequenceGenerator(name="users_id_seq", sequenceName= "users_id_seq", allocationSize=1)

    private Long id;
    @Column(unique = true)
    private String username;

//    private Long id;
//    @Column(unique = true)
//    private String Make;
}
