package com.hyl.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.hyl.parse.ppt.poi.service.common.CommonUtil.IMAGE_BATH_PATH;

public class Temp {
    public static void main(String[] args) {
        File file = new File(IMAGE_BATH_PATH + "su.jpg");
        System.out.println(file.exists());
    }

}
