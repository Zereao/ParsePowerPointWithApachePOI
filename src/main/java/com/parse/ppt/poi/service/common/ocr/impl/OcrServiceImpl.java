package com.parse.ppt.poi.service.common.ocr.impl;

import com.baidu.aip.ocr.AipOcr;
import com.parse.ppt.poi.service.common.ocr.OcrService;
import com.qcloud.image.ImageClient;
import com.qcloud.image.request.GeneralOcrRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Jupiter
 * @date 2018/3/26/19:33
 */
@Service
public class OcrServiceImpl implements OcrService {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Value("${baidu.app_id}")
    private String baidu_app_id;
    @Value("${baidu.api_key}")
    private String baidu_api_key;
    @Value("${baidu.secret_Key}")
    private String baidu_secret_key;
    @Value("${tencent.appId}")
    private String tencent_appId;
    @Value("${tencent.secretId}")
    private String tencent_secretId;
    @Value("${tencent.secretKey}")
    private String tencent_secretKey;
    @Value("${tencent.bucketName}")
    private String tencent_bucketName;

    @Override
    public List<String> getWordsWithBaiduOCR(String imgPath) {
        logger.info("------->  start!" +
                "   imgPath = " + imgPath);
        try {
            // 初始化一个AipOcr
            AipOcr client = new AipOcr(baidu_app_id, baidu_api_key, baidu_secret_key);
            // 传入可选参数调用接口
            HashMap<String, String> options = new HashMap<>();
            options.put("language_type", "CHN_ENG");
            options.put("detect_direction", "true");
            options.put("detect_language", "true");
            options.put("probability", "true");
            // 参数为本地图片路径
            JSONObject result = client.basicGeneral(imgPath, options);
            logger.info("------->   result = " + result.toString(2));
            // 解析 JSONObject，提取出其中所有的 的文本项，并将其添加到一个 List 中
            List<String> wordsList = new ArrayList<>();
            JSONArray childJsonArray = result.getJSONArray("words_result");
            for (Object jsonObj : childJsonArray) {
                String words = ((JSONObject) jsonObj).getString("words");
                wordsList.add(words);
            }
            logger.info("------->  end!   wordsList = " + wordsList);
            return wordsList;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 null");
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<String> getWordsWithTencentOCR(String imgPath) {
        logger.info("------->  start!" +
                "   imgPath = " + imgPath);
        try {
            // 初始化一个ImageClient
            ImageClient imageClient = new ImageClient(tencent_appId, tencent_secretId, tencent_secretKey);
            // 获取到 imgPath 对应的 File对象
            File imgFile = new File(imgPath);
            GeneralOcrRequest request = new GeneralOcrRequest(tencent_bucketName, imgFile);
            String resultStringOfJson = imageClient.generalOcr(request);
            logger.info("------->   result = " + resultStringOfJson);
            // 解析 这个 json 格式的String ———— resultStringOfJson，提取出其中所有的 的文本项，并将其添加到一个 List 中
            List<String> itemStringList = new ArrayList<>();
            // 使用 net.sf.json.JSONObject 的 fromObject()方法把 json格式的String 转换为 net.sf.json.JSONObject 对象
            net.sf.json.JSONObject resultJson = net.sf.json.JSONObject.fromObject(resultStringOfJson);
            net.sf.json.JSONArray childJsonArray = resultJson.getJSONObject("data").getJSONArray("items");
            for (Object itemJson : childJsonArray) {
                String itemstring = ((net.sf.json.JSONObject) itemJson).getString("itemstring");
                itemStringList.add(itemstring);
            }
            logger.info("------->  end!   itemStringList = " + itemStringList);
            return itemStringList;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 null");
            logger.error(e.getMessage());
        }
        return null;
    }
}
