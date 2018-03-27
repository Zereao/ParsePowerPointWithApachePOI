package com.parse.ppt.poi.service.common.poi.operate.xslf;

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

    /**
     * 判断PPTX页码数是否符合条件
     *
     * @param pptxFile   .PPTX格式的PPT文件
     * @param minPageNum 最小幻灯片张数
     * @return true-PPTX页码数大于或等于minPageNum<br>
     * false-PPTX页码数小于minPageNum
     */
    boolean isPageMatchCondition(File pptxFile, int minPageNum);
}
