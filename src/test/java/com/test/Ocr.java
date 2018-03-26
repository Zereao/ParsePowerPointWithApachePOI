package com.test;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * @author Jupiter
 * @date 2018/3/26/19:05
 */
public class Ocr {

    //设置APPID/AK/SK
    public static final String APP_ID = "10999155";
    public static final String API_KEY = "nmufkkqZM02LB9OYX8KlOkLm";
    public static final String SECRET_KEY = "4y7jm1jZvmjY1vt78Fo39f4tMWg1B4Zg";

    public static void main(String[] args) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        String path = "test.jpg";
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        System.out.println(res.toString(2));

    }


    @Test
    public void sample() throws IOException {
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");


        // 参数为本地图片路径
        String imgPath = "ZeroFilesOutput/baiduImgs/13.png";
        JSONObject res = client.basicGeneral(imgPath, options);
        System.out.println(res.toString(2));


//        File img = new File(imgPath);
//        FileInputStream inputStream = new FileInputStream(img);
//        inputStream.read();
//        byte[] file = null;
//        // 参数为本地图片二进制数组
////        byte[] file = readImageFile(image);
//        res = client.basicGeneral(file, options);
//        System.out.println(res.toString(2));


    }


}
