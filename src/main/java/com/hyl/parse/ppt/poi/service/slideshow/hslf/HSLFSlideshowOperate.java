package com.hyl.parse.ppt.poi.service.slideshow.hslf;

import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Slideshow 对应一个完整的PPT文件
 * 本类对应着 PPT 格式文件的操作，例如，新建PPT文件，读取PPT文件等。
 *
 * @author Jupiter
 */
@Service
public interface HSLFSlideshowOperate {
    /**
     * 创建一个新的空PPT格式的文件，初始大小为 640 x 480，创建路径为 /PPT文件
     *
     * @param pptName 需要创建的PPT的文件名
     * @throws IOException IOException
     */
    public void createNew4B3PPTFile(String pptName) throws IOException;


    /**
     * 创建一个新的空PPT格式的文件，初始大小为 960 x 540，创建路径为 /PPT文件
     *
     * @param pptName 需要创建的PPT的文件名
     * @throws IOException IOException
     */
    public void createNew16B9PPTFile(String pptName) throws IOException;


    /**
     * 读取PPT文件
     *
     * @param pptFileName PPT文件的文件名
     * @return PPT对象 HSLFSlideShow类型
     * @throws IOException IOException
     */
    public HSLFSlideShow loadPPT(String pptFileName) throws IOException;


    /**
     * 查看PPT文件幻灯页的大小
     *
     * @param ppt PPT对象  HSLFSlideShow ppt
     * @return 以字符串得形式返回幻灯页的大小，如果读取文件出错则返回 null
     */
    public String retrieveSlideSize(HSLFSlideShow ppt);


    /**
     * 修改幻灯页的大小
     *
     * @param ppt     PPT对象  HSLFSlideShow ppt
     * @param pptName PPT文件名
     * @param width   需要设置的幻灯页 宽度
     * @param height  需要设置的幻灯页 高度
     * @throws IOException 如果修改后的文件创建失败，则抛出异常
     */
    public void resizeSlide(HSLFSlideShow ppt, String pptName, int width, int height) throws IOException;


    /**
     * 保存PPT格式文件的修改，并将修改后的PPT文件放到 文件输出/PPT文件  目录下
     *
     * @param ppt PPT对象 HSLFSlideShow ppt；
     * @throws IOException IOException
     */
    public void saveChangeForPPT(HSLFSlideShow ppt, String pptName) throws IOException;
}
