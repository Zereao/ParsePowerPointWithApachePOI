package com.parse.ppt.poi.service.poi.hslf;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public interface PptOperateService {
    /**
     * 把PPT文件转换为PNG格式的图片，并且将其存储于 pptFile 所在的路径下的 PPT2IMG路径下
     *
     * @param pptFile .PPT格式的PPT文件
     * @return ReturnCode-返回码
     */
    String ppt2img(File pptFile);
}
