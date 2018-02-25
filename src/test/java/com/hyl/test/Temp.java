package com.hyl.test;

import java.io.File;

import static com.parse.ppt.poi.service.other.common.CommonUtil.IMAGE_BATH_PATH;

public class Temp {
    public static void main(String[] args) {
        File file = new File(IMAGE_BATH_PATH + "su.jpg");
        System.out.println(file.exists());
    }

}
