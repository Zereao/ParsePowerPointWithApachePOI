package com.parse.ppt.poi.entity;

/**
 * @author Jupiter
 * @version 2018/03/25 0:02
 */
public class User {
    private Integer id;
    private String username;
    private String email;
    private String mobile;
    private String password;
    private String mainPageEssayTitle;
    private String mainPageEssayContent;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMainPageEssayTitle() {
        return mainPageEssayTitle;
    }

    public void setMainPageEssayTitle(String mainPageEssayTitle) {
        this.mainPageEssayTitle = mainPageEssayTitle;
    }

    public String getMainPageEssayContent() {
        return mainPageEssayContent;
    }

    public void setMainPageEssayContent(String mainPageEssayContent) {
        this.mainPageEssayContent = mainPageEssayContent;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", mainPageEssayTitle='" + mainPageEssayTitle + '\'' +
                ", mainPageEssayContent='" + mainPageEssayContent + '\'' +
                '}';
    }
}
