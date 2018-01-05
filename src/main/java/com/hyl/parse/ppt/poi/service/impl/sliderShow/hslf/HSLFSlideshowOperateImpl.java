package com.hyl.parse.ppt.poi.service.impl.sliderShow.hslf;

import com.hyl.parse.ppt.poi.service.slideshow.hslf.HSLFSlideshowOperate;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFSlideShowImpl;

import java.io.*;

import java.awt.Dimension;

import static com.hyl.parse.ppt.poi.service.common.CommonUtil.PPT_BATH_PATH;

/**
 * @author Jupiter
 */
public class HSLFSlideshowOperateImpl implements HSLFSlideshowOperate {
    @Override
    public void createNew4B3PPTFile(String pptName) throws IOException {
        HSLFSlideShow ppt = new HSLFSlideShow();
        //默认大小为 640 x 480
        ppt.setPageSize(new Dimension(640, 480));
        //创建第一页新的幻灯片
        ppt.createSlide();
        //把这些更改保存到文件中
        saveChangeForPPT(ppt, pptName);
    }

    @Override
    public void createNew16B9PPTFile(String pptName) throws IOException {
        HSLFSlideShow ppt = new HSLFSlideShow();
        //设置大小为 960 x 540
        ppt.setPageSize(new Dimension(960, 540));
        //创建第一页新的幻灯片
        ppt.createSlide();
        //把这些更改保存到文件中
        saveChangeForPPT(ppt, pptName);
    }

    @Override
    public HSLFSlideShow loadPPT(String pptName) throws IOException {
        File pptFile = new File(PPT_BATH_PATH + pptName + ".ppt");
        InputStream pptFileInputStream = new FileInputStream(pptFile);
        return new HSLFSlideShow(new HSLFSlideShowImpl(pptFileInputStream));
    }

    @Override
    public String retrieveSlideSize(HSLFSlideShow ppt) {
        Dimension pgSize = ppt.getPageSize();
        //幻灯片宽度
        int pgX = pgSize.width;
        //幻灯片高度
        int pgY = pgSize.height;
        return "长：" + pgX + "；宽：" + pgY;
    }

    @Override
    public void resizeSlide(HSLFSlideShow ppt, String pptName, int width, int height) throws IOException {
        ppt.setPageSize(new Dimension(width, height));
        File newPPT = new File(PPT_BATH_PATH + pptName + "_[" + width + "x" + height + "].ppt");
        FileOutputStream out = new FileOutputStream(newPPT);
        ppt.write(out);
        out.close();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public void saveChangeForPPT(HSLFSlideShow ppt, String pptName) throws IOException {
        File file = new File(PPT_BATH_PATH);
        // 如果目标文件夹不存在，则新建文件夹
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(PPT_BATH_PATH + pptName + ".ppt");
        FileOutputStream out = new FileOutputStream(file);
        ppt.write(out);
        out.close();
    }

}
