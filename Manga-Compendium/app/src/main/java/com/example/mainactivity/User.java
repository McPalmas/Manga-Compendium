package com.example.mainactivity;

import java.io.Serializable;

public class User implements Serializable {

    private String username;
    private String password;
    private String email;
    private Integer id;
    private String img;


    public User(Integer id,String username, String email, String password, String img) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email=email;
        this.img=img;
    }

    public User(String username, String email, String password, String img) {
        this.username = username;
        this.password = password;
        this.email=email;
        this.img=img;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString(){
        String s;
        s = username +" "+password;
        return s;

    }
}
