package com.parse.ppt.poi.entity;

/**
 * 使用爬虫技术从 从 第1PPT http://www.1ppt.com/ 网站上直接获取到的现成的PPT的信息
 *
 * @author Jupiter
 * @date 2018/03/05/18:39
 */
public class No1PPT {
    private Integer id;
    private String srcDescription;
    private String srcImgUrl;
    private String downloadUrl;

    public No1PPT() {
    }

    public No1PPT(String srcDescription, String srcImgUrl, String downloadUrl) {
        this.srcDescription = srcDescription;
        this.srcImgUrl = srcImgUrl;
        this.downloadUrl = downloadUrl;
    }

    public No1PPT(Integer id, String srcDescription, String srcImgUrl, String downloadUrl) {
        this.id = id;
        this.srcDescription = srcDescription;
        this.srcImgUrl = srcImgUrl;
        this.downloadUrl = downloadUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSrcDescription() {
        return srcDescription;
    }

    public void setSrcDescription(String srcDescription) {
        this.srcDescription = srcDescription;
    }

    public String getSrcImgUrl() {
        return srcImgUrl;
    }

    public void setSrcImgUrl(String srcImgUrl) {
        this.srcImgUrl = srcImgUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @Override
    public String toString() {
        return "No1PPT{" +
                "id=" + id +
                ", srcDescription='" + srcDescription + '\'' +
                ", srcImgUrl='" + srcImgUrl + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                '}';
    }
}
