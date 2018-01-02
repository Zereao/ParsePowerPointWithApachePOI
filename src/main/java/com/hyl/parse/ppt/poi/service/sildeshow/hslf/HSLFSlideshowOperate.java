package com.hyl.parse.ppt.poi.service.sildeshow.hslf;

import java.io.IOException;

/**
 * 对应一个完整的PPT文件
 *
 * @author Jupiter
 */

public interface HSLFSlideshowOperate {
    /**
     * 创建一个新的空PPT格式的文件，初始大小为 640 x 480
     *
     * @param pptName 需要创建的PPT的文件名
     * @throws IOException IOException
     */
    public void createNewPPTFile(String pptName) throws IOException;

    /**
     * 设置幻灯片的标题，该方法实现的逻辑是 在一个空白的PPT文件中新建一页幻灯片，然后加入标题并保存。
     *
     * @param pptName PPT文件的文件名
     * @param title   幻灯页需要修改成的标题
     * @throws IOException IOException
     */
    public void setSlideTitle(String pptName, String title) throws IOException;

    /**
     * 获得某张幻灯片中的所有形状
     *
     * @param pptName PPT文件名
     */
    public void getShapes(String pptName);

    /**
     * 解压出幻灯片中的所有图片
     *
     * @param pptName PPT文件名
     * @throws IOException IOException
     */
    public void extractAllPictures(String pptName) throws IOException;

    /**
     * 将一张图片增加到当前PPT文件里的新的一页中
     *
     * @param pptName PPT文件名
     * @param picName 需要添加的图片的文件名
     * @throws IOException IOException
     */
    public void addPicture(String pptName, String picName) throws IOException;

    /**
     * 修改幻灯片的背景，这个方法的作用是   新建一个ppt文件，然后设置幻灯片的背景
     *
     * @param pptName ppt文件名
     * @param picName 需要替换为背景的图片名
     * @throws IOException IOException
     */
    public void changeBackgroundOfSlide(String pptName, String picName) throws IOException;


    public void changeBackgroundOfShape(String pptName) throws IOException;
}
