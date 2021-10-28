package com.clickandeat.finalproject5.Model;

import android.widget.EditText;

public class userModel {

    String name, email, password ,address ,profileImg;
    int phone;

    public userModel(String name, String email, String password, String address, String profileImg, int phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.profileImg = profileImg;
        this.phone = phone;
    }
    public userModel(){}

    public userModel(String name, String email, String password, String address, int phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }




}
