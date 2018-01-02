package com.hyl.parse.ppt.poi.service.slide.hslf;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.Map;

/**
 * Slider 对应每一张幻灯片
 *
 * @author Jupiter
 */
public interface HSLFSlideOperate {
    /**
     * 查看PPT文件幻灯页的大小
     *
     * @param pptName PPT文件名
     * @return 以字符串得形式返回幻灯页的大小，如果读取文件出错则返回 null
     */
    public String retrieveSlideSize(String pptName);


    /**
     * 修改幻灯页的大小
     *
     * @param pptName PPT文件名
     * @param width   需要设置的幻灯页 宽度
     * @param height  需要设置的幻灯页 高度
     * @throws IOException 如果修改后的文件创建失败，则抛出异常
     */
    public void resizeSlideSize(String pptName, int width, int height) throws IOException;

    /**
     * @param pptName PPT文件名
     * @return 返回一个Map，key = shapeName , value = Anchor
     */
    public Map<String, Rectangle2D> getShapes(String pptName);
}
