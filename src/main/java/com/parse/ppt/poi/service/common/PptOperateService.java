package com.parse.ppt.poi.service.common;

import com.parse.ppt.poi.entity.No1PPT;
import com.parse.ppt.poi.entity.PoiPPT;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public interface PptOperateService {

    /**
     * 把No1PPT文件的所有幻灯片转换为PNG格式的图片
     *
     * @param no1PPT .PPT格式的PPT文件
     * @return 幻灯页张数 - 错误则返回-1
     */
    int ppt2img(No1PPT no1PPT);

    /**
     * 把PoiPPT文件的所有幻灯片转换为PNG格式的图片
     *
     * @param poiPPT .PPT格式的PPT文件
     * @return 幻灯页张数 - 错误则返回-1
     */
    int ppt2img(PoiPPT poiPPT);

    /**
     * 重建PPT，去掉NoPPT对象对应的PPT文件中的广告页
     *
     * @param no1PPT       需要修改的No1PPT对象
     * @param adPageIndexs 包含广告页面index的数组
     * @return ReturnCode-返回码
     */
    String rebuildPPT(No1PPT no1PPT, int[] adPageIndexs);


}
