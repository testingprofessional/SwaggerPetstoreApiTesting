package com.freenow.blog.models;

import java.util.Random;

public class User {
    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private int userStatus;

    public User(String username) {
        this.id = 765432;
        this.username = username;
        this.firstname = "testfirstname";
        this.lastname = "testlastname";
        this.email = "testemail@email.com";
        this.password = "testpassword";
        this.phone = "0612345678";
        this.userStatus = 0;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String changedPhone) {
        this.phone = changedPhone;
    }

    public int getUserStatus() {
        return userStatus;
    }
}
