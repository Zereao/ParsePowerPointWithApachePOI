package com.parse.ppt.poi.service.common.ppt2img;

import org.springframework.stereotype.Service;

/**
 * 把PPT/PPTX的每一张幻灯片都转化为图片的Service
 *
 * @author Jupiter
 * @date 2018/3/25/10:58
 */
@Service
public interface Ppt2ImgService {
    /**
     * 把 PPT文件转换为PNG格式的图片
     *
     * @param pptId  PPT对象的ID，对应的PPT文件可以是.PPT格式的，也可以是.PPTX格式的
     * @param pptTag 用来标识是 No1PPT 还是 poiPPT
     * @return ReturnCode-返回码
     */
    String ppt2imgs(String pptId, String pptTag);

    /**
     * 获取某一个PPT转换成图片的图片张数，即获取对应目录下的图片张数目
     *
     * @param pptId  PPT对象的ID
     * @param pptTag 用来标识是 No1PPT 还是 poiPPT
     * @return 某一个PPT转换成图片的图片张数
     */
    int getImgsNum(String pptId, String pptTag);
}
