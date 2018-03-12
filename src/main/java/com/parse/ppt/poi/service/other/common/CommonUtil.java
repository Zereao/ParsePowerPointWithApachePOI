package com.parse.ppt.poi.service.other.common;

import java.io.File;

/**
 * 包含一些公共的变量和方法
 *
 * @author Jupiter
 */
public class CommonUtil {
    public static final String PPT_BATH_PATH = "文件输出/PPT文件/";
    public static final String PPTX_BATH_PATH = "文件输出/PPTX文件/";
    public static final String IMAGE_BATH_PATH = "文件输出/图片输出/";


    /**
     * 得到图片正确的扩展名
     *
     * @param picName PicName文件名
     * @return 图片的扩展名  .JPG  .JPEG   .PNG
     */
    public static String getPicExt(String picName) {
        if (new File(IMAGE_BATH_PATH + picName + ".JPG").exists()) {
            return ".jpg";
        } else if (new File(IMAGE_BATH_PATH + picName + ".JPEG").exists()) {
            return ".jpeg";
        } else if(new File(IMAGE_BATH_PATH + picName + ".PNG").exists()) {
            return ".png";
        } else if(new File(IMAGE_BATH_PATH + picName + ".GIF").exists()) {
            return ".gif";
        } else if(new File(IMAGE_BATH_PATH + picName + ".BMP").exists()) {
            return ".bmp";
        } else {
            return "File [ "+ IMAGE_BATH_PATH + picName +" .jpg/.jpeg/.png/.bmp/.gif/.bmp ] Not Exists !";
        }
    }
}
