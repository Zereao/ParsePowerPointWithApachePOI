package com.test;

import com.parse.ppt.poi.service.common.other.impl.sliderShow.hslf.HSLFSlideshowOperateImpl;
import com.parse.ppt.poi.service.common.other.impl.slide.hslf.HSLFSlideOperateImpl;
import com.parse.ppt.poi.service.common.other.slide.hslf.HSLFSlideOperate;
import com.parse.ppt.poi.service.common.other.slideshow.hslf.HSLFSlideshowOperate;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;

import java.io.*;


public class Main {
    public static void main(String[] args) {
        HSLFSlideshowOperate slideOperate = new HSLFSlideshowOperateImpl();
        HSLFSlideOperate slideshowOperate = new HSLFSlideOperateImpl();

        try {
            HSLFSlideShow ppt = slideOperate.loadPPT("16-9");
            String size = slideOperate.retrieveSlideSize(ppt);
            System.out.println(size);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
