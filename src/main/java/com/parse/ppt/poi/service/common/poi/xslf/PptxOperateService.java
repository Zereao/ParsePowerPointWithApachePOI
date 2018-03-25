package com.parse.ppt.poi.service.common.poi.xslf;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public interface PptxOperateService {
    /**
     * 把PPTX文件转换为PNG格式的图片
     *
     * @param pptxFile   .PPTX格式的PPT文件
     * @param targetPath 输出文件夹
     * @return ReturnCode-返回码
     */
    String pptx2img(File pptxFile, String targetPath);
}
