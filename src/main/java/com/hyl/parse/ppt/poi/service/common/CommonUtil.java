package com.hyl.parse.ppt.poi.service.common;

import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFSlideShowImpl;

import java.io.*;

/**
 * 包含一些公共的方法
 *
 * @author Jupiter
 */
public class CommonUtil {
    public static final String PPT_BATH_PATH = "PPT文件/";
    public static final String PPTX_BATH_PATH = "PPTX文件/";

    /**
     * 读取 ppt 文件
     *
     * @param pptName ppt文件名
     * @return PPT对象
     */
    public static HSLFSlideShow loadPPT(String pptName) {
        File pptFile = new File(PPT_BATH_PATH + pptName + ".ppt");
        InputStream pptFileInputStream = null;
        HSLFSlideShow ppt = null;
        try {
            pptFileInputStream = new FileInputStream(pptFile);
            ppt = new HSLFSlideShow(new HSLFSlideShowImpl(pptFileInputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ppt;
    }

}
