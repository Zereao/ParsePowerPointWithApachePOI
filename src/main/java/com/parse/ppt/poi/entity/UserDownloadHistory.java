package com.parse.ppt.poi.entity;

/**
 * @author Jupiter
 * @date 2018/03/08/18:14
 */
public class UserDownloadHistory {
    private int id;
    private String email;
    private int pptId;

    public UserDownloadHistory() {
    }

    public UserDownloadHistory(String email, int pptId) {
        this.email = email;
        this.pptId = pptId;
    }

    public UserDownloadHistory(int id, String email, int pptId) {
        this.id = id;
        this.email = email;
        this.pptId = pptId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPptId() {
        return pptId;
    }

    public void setPptId(int pptId) {
        this.pptId = pptId;
    }

    @Override
    public String toString() {
        return "UserDownloadHistory{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", pptId=" + pptId +
                '}';
    }
}
