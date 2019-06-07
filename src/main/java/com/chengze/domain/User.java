package com.chengze.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="users")
public class User implements Serializable, UserDetails {
    public interface WithoutPasswordView {};
    public interface WithPasswordView extends WithoutPasswordView {};

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

    @Column(name = "account_Non_Expired")
    private Boolean accountNonExpired;

    @Column(name = "account_Non_Locked")
    private Boolean accountNonLocked;

    @Column(name = "Credentials_Non_Expired")
    private Boolean credentialsNonExpired;

    @Column(name = "is_Enabled")
    private Boolean enable;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Car> cars;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Authority> authorities;


    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    @JsonView(WithoutPasswordView.class)
    public String getUsername(){

        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired ) {

        this.accountNonExpired = accountNonExpired;
    }
    public boolean getAccountNonExpired(){

        return accountNonExpired;
    }


    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }


    public void setAccountNonLocked(boolean accountNonLocked ) {

        this.accountNonLocked = accountNonLocked;
    }
    public boolean getAccountNonLocked(){

        return accountNonLocked;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }


    public void setCredentialsNonExpired(boolean credentialsNonExpired ) {

        this.credentialsNonExpired = credentialsNonExpired;
    }
    public boolean getCredentialsNonExpired(){

        return credentialsNonExpired;
    }


    @Override
    public boolean isEnabled() {
        return enable;
    }

    public void setEnabled(boolean enable ) {

        this.enable = enable;
    }
    public boolean getEnabled(){

        return enable;
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


    @Override
//    @JsonIgnore
    @JsonView(WithPasswordView.class)
    public String getPassword() {
        return password;
    }
    @JsonProperty
    public void setPassword(String password) {

        this.password = password;
    }



}
