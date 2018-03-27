package com.parse.ppt.poi.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jupiter
 * @date 2018/3/27/14:07
 */
public class AdWordAnalyseUtil {

    /**
     * 广告信息分词，结合Ocr识别用来筛选幻灯片内容，人工配置分词，有待优化 - 【 key - 权重 】
     */
    public static Map<String, Double> adKeywordsMap = new HashMap<>();

    // 静态块，设置 关键词及其权重
    static {
        adKeywordsMap.put("www", 1.0);
        adKeywordsMap.put("www.", 2.0);
        adKeywordsMap.put("1ppt", 4.5);
        adKeywordsMap.put("www.1ppt", 4.5);
        adKeywordsMap.put("wwww.1ppt", 5.0);
        adKeywordsMap.put("第一", 1.0);
        adKeywordsMap.put("第1", 1.0);
        adKeywordsMap.put("第1PPT", 5.0);
        adKeywordsMap.put("付费", 3.0);
        adKeywordsMap.put("免费", 3.0);
        adKeywordsMap.put("在线付费", 3.5);
        adKeywordsMap.put("免费下载", 3.5);
        adKeywordsMap.put("下载", 1.0);
        adKeywordsMap.put("ppt素材", 2.0);
        adKeywordsMap.put("素材下载", 2.0);
        adKeywordsMap.put("ppt素材下载", 3.0);
        adKeywordsMap.put("可以在", 2.5);
        adKeywordsMap.put("下列情况使用", 5.0);
        adKeywordsMap.put("不限次数", 2.0);
        adKeywordsMap.put("商业演示", 3.0);

        adKeywordsMap.put("微信", 2.0);
        adKeywordsMap.put("扫描", 2.0);
        adKeywordsMap.put("关注微", 2.5);
        adKeywordsMap.put("关注微信", 3.5);
        adKeywordsMap.put("后台回复", 3.5);
        adKeywordsMap.put("关注微信公", 4.0);
        adKeywordsMap.put("关注微信公众号", 5.0);
        adKeywordsMap.put("二维码", 2.5);
        adKeywordsMap.put("完整版", 2.5);
        adKeywordsMap.put("扫描二维码", 4.0);
        adKeywordsMap.put("本模板总共", 4.0);
        adKeywordsMap.put("获取完整版", 4.0);
        adKeywordsMap.put("微信扫描二维", 4.5);
        adKeywordsMap.put("微信扫描二维码", 5.0);
    }
}
