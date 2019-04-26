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
    private List<Car> car;

    public Long getId() {
        return id;
    }

    public void setUsername(String user_name) { username = user_name; }
    public String getUsername() { return username ; }

    public void setFirstname(String firstName) { firstName = firstName; }
    public String getFirstname() { return username ; }

    public void setLastname(String lastName) { lastName = lastName; }
    public String getLastname() { return lastName ; }

    public void setEmail(String email) { email= email; }
    public String getEmail() { return email ; }

    public void setPassword(String password) { password = password; }
    public String getPassword() { return password ; }
}
