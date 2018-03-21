package com.parse.ppt.poi.service.poi.xslf;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public interface PptxOperateService {
    /**
     * 把PPTX文件转换为PNG格式的图片，并且将其存储于 pptxFile 所在的路径下的 PPT2IMG 路径下
     *
     * @param pptxFile .PPTX格式的PPT文件
     * @return ReturnCode-返回码
     */
    String pptx2img(File pptxFile);
}
