package com.hyl.test;

import com.hyl.parse.ppt.poi.service.impl.slide.hslf.HSLFSlideOperateImpl;
import com.hyl.parse.ppt.poi.service.impl.sliderShow.hslf.HSLFSlideshowOperateImpl;
import com.hyl.parse.ppt.poi.service.sildeshow.hslf.HSLFSlideshowOperate;
import com.hyl.parse.ppt.poi.service.slide.hslf.HSLFSlideOperate;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        HSLFSlideOperate slideOperate = new HSLFSlideOperateImpl();
        HSLFSlideshowOperate slideshowOperate = new HSLFSlideshowOperateImpl();
        try {
            slideshowOperate.extractAllPictures("枫叶");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
