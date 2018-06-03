package com.parse.ppt.poi.entity;

/**
 * 使用Apache-POI 技术操作过的PPT对象
 *
 * @author Jupiter
 * @version 2018/3/25/10:38
 */
public class PoiPPT {
    private Integer id;
    private String description;
    private Integer no1pptId;
    private String fileName;
    private String pageNum;
    private String filePath;
    private String thumbnailPath;
    private String fileExt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNo1pptId() {
        return no1pptId;
    }

    public void setNo1pptId(Integer no1pptId) {
        this.no1pptId = no1pptId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    @Override
    public String toString() {
        return "PoiPPT{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", no1pptId=" + no1pptId +
                ", fileName='" + fileName + '\'' +
                ", pageNum='" + pageNum + '\'' +
                ", filePath='" + filePath + '\'' +
                ", thumbnailPath='" + thumbnailPath + '\'' +
                ", fileExt='" + fileExt + '\'' +
                '}';
    }
}
