package com.models;
import java.io.Serializable;

public class User implements Serializable {

    private Integer userid;
    private String email;
    private String password;
    private String role;
    private String name;
    private String address;
    private String telno;
    private String dob;
    private String gender;
    private String creationDate;

    public User(){}

    public User(String email, String password, String role, String name, String address, String telno, String dob, String gender, String creationDate){
        this.email = email;
        this.password = password;
        this.role = role;
        this.name = name;
        this.address = address;
        this.telno = telno;
        this.dob = dob;
        this.gender = gender;
        this.creationDate = creationDate;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString()
    {
        String order = "[" + this.userid + "," + this.email + "," + this.address + "," + this.dob + "," + this.telno + "," + this.gender + "," + this.creationDate + "]";
        return order;
    }
}