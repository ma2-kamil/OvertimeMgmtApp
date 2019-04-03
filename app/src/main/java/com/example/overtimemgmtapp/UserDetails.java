package com.example.overtimemgmtapp;

public class UserDetails {

    final String fullname;
    final String username;
    final String password;
    final String email;
    final String shiftmanager;
    final String uniquecode;

    // user class so we can store all data when received from login page.

    public UserDetails(String fullname, String username, String password, String email,
                       String shiftmanager, String uniquecode){
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.shiftmanager = shiftmanager;
        this.uniquecode = uniquecode;


    }

    public String getFullname() {
        return fullname;
    }



}
