package com.parse.ppt.poi.service.common.other.slide.hslf;

import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.springframework.stereotype.Service;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Slide 对应每一张幻灯片
 *
 * @author Jupiter
 */
@Service
public interface HSLFSlideOperate {

    /**
     * 设置幻灯片的标题，该方法实现的逻辑是 在一个空白的PPT文件中新建一页幻灯片，然后加入标题并保存。
     *
     * @param ppt   ppt对象 HSLFSlideShow ppt
     * @param title 幻灯页需要修改成的标题
     */
    public void setSlideTitle(HSLFSlideShow ppt, String title);


    /**
     * 获得某张幻灯片中的所有形状
     *
     * @param slide 代表某一也幻灯片 HSLFSlide slide
     */
    public List<Map<String, Rectangle2D>> getShapes(HSLFSlide slide);


    /**
     * 获得PPT所有幻灯片中的所有形状<BR/>
     * Map< Integer - 第几张幻灯片的index , List< Map< String, Rectangle2D>> ><BR/>
     * List< Map< String, Rectangle2D>> - 某一张幻灯片中所有 Shape-Anchor Map的 List<BR/>
     * Map< String - shapeName, Rectangle2D - 锚点 Anchor 定义了 Shape 的位置>
     *
     * @param ppt ppt对象 HSLFSlideShow ppt
     */
    public Map<Integer, List<Map<String, Rectangle2D>>> getAllShapes(HSLFSlideShow ppt);


    /**
     * 解压出幻灯片中的所有图片
     *
     * @param ppt ppt对象 HSLFSlideShow ppt
     * @throws IOException IOException
     */
    public void extractAllPictures(HSLFSlideShow ppt) throws IOException;


    /**
     * 将一张图片增加到当前PPT文件里的新的一页中，只作 相关方法调用的 示例
     *
     * @param ppt     ppt对象 HSLFSlideShow ppt
     * @param picName 需要添加的图片的文件名
     * @throws IOException IOException
     */
    public void addPicture(HSLFSlideShow ppt, String picName) throws IOException;


    /**
     * 修改幻灯片的背景，这个方法的作用是   新建一个ppt文件，然后设置幻灯片的背景
     *
     * @param ppt     ppt对象 HSLFSlideShow ppt
     * @param picName 需要替换为背景的图片名
     * @throws IOException IOException
     */
    public void changeBackgroundOfSlide(HSLFSlideShow ppt, String picName) throws IOException;


    public void changeBackgroundOfShape(HSLFSlideShow ppt);
}
