package com.cse3310.cse3310_group_one_project.Models;

import java.io.Serializable;

/**
 * Created by Arsalan on 4/12/2018.
 */

public class User implements Serializable {
    private int id;
    private String Fname;
    private String Lname;
    private String username;
    private String password;
    private String accountType;
    private String phoneNumber;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getFname() { return Fname; }

    public void setFname(String fname) { Fname = fname; }

    public String getLname() { return Lname; }

    public void setLname(String lname) {Lname = lname; }

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

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
