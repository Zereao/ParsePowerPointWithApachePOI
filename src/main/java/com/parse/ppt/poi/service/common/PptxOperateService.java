package com.parse.ppt.poi.service.common;

import com.parse.ppt.poi.entity.No1PPT;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
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

    /**
     * 重建PPTX，去掉NoPPT对象对应的PPTX文件中的广告页
     *
     * @param no1PPT       需要修改的No1PPT对象
     * @param adPageIndexs 包含广告页面index的数组
     * @return ReturnCode-返回码
     */
    String rebuildPPTX(No1PPT no1PPT, int[] adPageIndexs);
}
