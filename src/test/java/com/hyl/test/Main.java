package com.hyl.test;

import com.hyl.parse.ppt.poi.service.other.impl.sliderShow.hslf.HSLFSlideshowOperateImpl;
import com.hyl.parse.ppt.poi.service.other.impl.slide.hslf.HSLFSlideOperateImpl;
import com.hyl.parse.ppt.poi.service.other.slide.hslf.HSLFSlideOperate;
import com.hyl.parse.ppt.poi.service.other.slideshow.hslf.HSLFSlideshowOperate;
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
