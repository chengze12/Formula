package com.chengze.extend.security;

public class RestAuthenticaitionRequest {
    private String username;
    private String password;

    public RestAuthenticaitionRequest(){};
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username=username;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }
}
