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
    private String typeTag;
    private Integer no1pptId;
    private String pptImgAddress;
    private String pptDownloadUrl;
    private String pptFileName;

    public PoiPPT() {
    }

    public PoiPPT(String pptDescription, String typeTag) {
        this.pptDescription = pptDescription;
        this.typeTag = typeTag;
    }

    public PoiPPT(String pptDescription, String typeTag, Integer no1pptId) {
        this.pptDescription = pptDescription;
        this.typeTag = typeTag;
        this.no1pptId = no1pptId;
    }

    public PoiPPT(String pptDescription, String typeTag, Integer no1pptId, String pptImgAddress, String pptDownloadUrl, String pptFileName) {
        this.pptDescription = pptDescription;
        this.typeTag = typeTag;
        this.no1pptId = no1pptId;
        this.pptImgAddress = pptImgAddress;
        this.pptDownloadUrl = pptDownloadUrl;
        this.pptFileName = pptFileName;
    }

    public PoiPPT(Integer id, String pptDescription, String typeTag, Integer no1pptId, String pptImgAddress, String pptDownloadUrl, String pptFileName) {
        this.id = id;
        this.pptDescription = pptDescription;
        this.typeTag = typeTag;
        this.no1pptId = no1pptId;
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

    public String getTypeTag() {
        return typeTag;
    }

    public void setTypeTag(String typeTag) {
        this.typeTag = typeTag;
    }

    public Integer getNo1pptId() {
        return no1pptId;
    }

    public void setNo1pptId(Integer no1pptId) {
        this.no1pptId = no1pptId;
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
                ", typeTag='" + typeTag + '\'' +
                ", no1pptId=" + no1pptId +
                ", pptImgAddress='" + pptImgAddress + '\'' +
                ", pptDownloadUrl='" + pptDownloadUrl + '\'' +
                ", pptFileName='" + pptFileName + '\'' +
                '}';
    }
}