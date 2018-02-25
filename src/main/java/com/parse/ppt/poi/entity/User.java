package com.parse.ppt.poi.entity;

public class User {
    private Integer id;
    private String username;
    private String email;
    private String phoneNum;
    private String password;

    public User() {
    }

    public User(String username, String email, String phoneNum, String password) {
        this.username = username;
        this.email = email;
        this.phoneNum = phoneNum;
        this.password = password;
    }

    public User(Integer id, String username, String email, String phoneNum, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phoneNum = phoneNum;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
