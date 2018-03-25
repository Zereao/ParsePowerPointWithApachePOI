package com.parse.ppt.poi.entity;

/**
 * 使用Apache-POI 技术操作过的PPT对象
 *
 * @author Jupiter
 * @date 2018/3/25/10:38
 */
public class PoiPPT {
    private Integer id;
    private String pptDescription;
    private String pptImgAddress;
    private String pptDownloadUrl;
    private String pptFileName;

    public PoiPPT() {
    }

    public PoiPPT(Integer id, String pptDescription, String pptImgAddress, String pptDownloadUrl) {
        this.id = id;
        this.pptDescription = pptDescription;
        this.pptImgAddress = pptImgAddress;
        this.pptDownloadUrl = pptDownloadUrl;
    }

    public PoiPPT(Integer id, String pptDescription, String pptImgAddress, String pptDownloadUrl, String pptFileName) {
        this.id = id;
        this.pptDescription = pptDescription;
        this.pptImgAddress = pptImgAddress;
        this.pptDownloadUrl = pptDownloadUrl;
        this.pptFileName = pptFileName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPptDescription() {
        return pptDescription;
    }

    public void setPptDescription(String pptDescription) {
        this.pptDescription = pptDescription;
    }

    public String getPptImgAddress() {
        return pptImgAddress;
    }

    public void setPptImgAddress(String pptImgAddress) {
        this.pptImgAddress = pptImgAddress;
    }

    public String getPptDownloadUrl() {
        return pptDownloadUrl;
    }

    public void setPptDownloadUrl(String pptDownloadUrl) {
        this.pptDownloadUrl = pptDownloadUrl;
    }

    public String getPptFileName() {
        return pptFileName;
    }

    public void setPptFileName(String pptFileName) {
        this.pptFileName = pptFileName;
    }

    @Override
    public String toString() {
        return "PoiPPT{" +
                "id=" + id +
                ", pptDescription='" + pptDescription + '\'' +
                ", pptImgAddress='" + pptImgAddress + '\'' +
                ", pptDownloadUrl='" + pptDownloadUrl + '\'' +
                ", pptFileName='" + pptFileName + '\'' +
                '}';
    }
}
