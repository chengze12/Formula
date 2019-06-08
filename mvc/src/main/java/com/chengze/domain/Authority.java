package com.chengze.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;


@Entity
@Table(name="authorities")
public class Authority implements Serializable, GrantedAuthority {

    @Id
    @GeneratedValue(strategy=SEQUENCE, generator="authorities_id_seq")
    @SequenceGenerator(name="authorities_id_seq", sequenceName="authorities_id_seq", allocationSize=1)
    private Long id;

    @Column(name="role")
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Authority(){}
    public Authority(User user, String authority){
        this.user=user;
        this.role=authority;
    }

    public Long getId(){
        return id;
    }

    public void setRole(String role){
        this.role=role;
    }
    public String getRole() {
        return role;
    }



    public void setUser(User u) {
        this.user = u;
    }

    public User getUser(){
        return user;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
