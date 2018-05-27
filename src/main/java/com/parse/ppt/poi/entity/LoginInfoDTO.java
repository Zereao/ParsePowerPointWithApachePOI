package com.parse.ppt.poi.entity;

/**
 * 用户登陆时，用于传递参数的DTO
 *
 * @author Jupiter
 * @version 2018/04/25 23:44
 */
public class LoginInfoDTO {
    private String account;
    private String password;
    private String rememberTag;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRememberTag() {
        return rememberTag;
    }

    public void setRememberTag(String rememberTag) {
        this.rememberTag = rememberTag;
    }

    @Override
    public String toString() {
        return "LoginInfoDTO{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", rememberTag='" + rememberTag + '\'' +
                '}';
    }
}
