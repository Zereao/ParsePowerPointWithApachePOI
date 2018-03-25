package com.parse.ppt.poi.service.common.poi.hslf;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public interface PptOperateService {
    /**
     * 把PPT文件转换为PNG格式的图片
     *
     * @param pptFile    .PPT格式的PPT文件
     * @param targetPath 输出文件夹
     * @return ReturnCode-返回码
     */
    String ppt2img(File pptFile, String targetPath);
}
