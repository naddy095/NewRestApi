package com.example.newrestapi;

/**
 * Created by nadeem on 07-06-2015.
 */
public class NewUser {
    private String phoneNumber;
    private String username;
    private String name;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
