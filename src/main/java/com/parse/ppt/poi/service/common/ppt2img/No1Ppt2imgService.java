package com.parse.ppt.poi.service.common.ppt2img;

import org.springframework.stereotype.Service;

@Service
public interface No1Ppt2imgService {
    /**
     * 把No1PPT - PPT文件转换为PNG格式的图片，并且将其存储于 pptFile 所在的路径下的 PPT2IMG 路径下
     *
     * @param no1PptID NO1PPT的ID，对应的PPT文件可以是.PPT格式的，也可以是.PPTX格式的
     * @return ReturnCode-返回码
     */
    String ppt2img(String no1PptID);
}
