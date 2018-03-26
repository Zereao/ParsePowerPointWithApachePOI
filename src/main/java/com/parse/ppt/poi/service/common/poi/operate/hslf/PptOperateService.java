package com.parse.ppt.poi.service.common.poi.operate.hslf;

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

    /**
     * 判断PPT页码数是否符合条件
     *
     * @param pptFile    .PPT格式的PPT文件
     * @param minPageNum 最小幻灯片张数
     * @return true-PPT页码数大于或等于minPageNum<br>
     * false-PPT页码数小于minPageNum
     */
    boolean isPageMatchCondition(File pptFile, int minPageNum);
}
