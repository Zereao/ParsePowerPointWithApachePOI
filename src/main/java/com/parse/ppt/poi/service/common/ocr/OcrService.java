package com.parse.ppt.poi.service.common.ocr;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jupiter
 * @date 2018/3/26/19:33
 */
@Service
public interface OcrService {
    /**
     * 使用百度OCR接口，提取图片中的文字<br>
     * 每天可以免费识别500次<br>
     * <strong>fuck 垃圾百度</strong>
     *
     * @param imgPath 需要识别的图片的路径
     * @return 识别结果，把提取出来的所有 words 项都存到List 中
     */
    List<String> getWordsWithBaiduOCR(String imgPath);

    /**
     * 使用腾讯云OCR接口，提取图片中的文字<br>
     * 每天可以免费识别1000次<br>
     * <strong>【比垃圾百度要强大】</strong>
     *
     * @param imgPath 需要识别的图片的路径
     * @return 识别结果, 把提取出来的所有 words 项都存到List 中
     */
    List<String> getWordsWithTencentOCR(String imgPath);
}
