package com.parse.ppt.poi.service.common;

import com.parse.ppt.poi.entity.No1PPT;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 把PPT/PPTX的每一张幻灯片都转化为图片的Service
 *
 * @author Jupiter
 * @date 2018/3/25/10:58
 */
@Service
public interface PoiService {
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

    /**
     * 从 No1PPT 集合 no1PPTCollection 中挑选出幻灯片张数 ≥ minPage 的 No1PPT对象，最多取 elemNum = 80，最多取80个元素
     *
     * @param no1PPTCollection No1PPTCollection
     * @param minPageNum       最小幻灯片张数
     * @param elemNum          返回List中最多元素个数
     * @return 符合条件的No1PptList
     */
    List<No1PPT> selectPPTByPageNum(Collection<No1PPT> no1PPTCollection, int minPageNum, int elemNum);

    /**
     * 从 No1PPT 集合 no1PPTCollection 中挑选出幻灯片张数 ≥ minPage 的 No1PPT对象
     *
     * @param no1PPTCollection No1PPTCollection
     * @param minPageNum       最小幻灯片张数
     * @return 符合条件的No1PptList
     */
    List<No1PPT> selectPPTByPageNum(Collection<No1PPT> no1PPTCollection, int minPageNum);

    /**
     * 从 No1PPT 集合 no1PPTCollection 中挑选出幻灯片张数 ≥ minPage 的 No1PPT对象，
     * 以及包含广告的页面index，将这些信息存储于一个Map中，最后将这个mapList返回
     *
     * @param no1PPT     需要处理的No1PPT对象
     * @param minPageNum 最小幻灯片张数
     * @return key-No1PPT对象，包含广告的页面的index数组，最后返回的是一个Map<br>
     * no1ppt-int[]  ： 准备生成该PoiPPT对象<br>
     * no1ppt-null  ： 数据库中已经存在对应PoiPPT对象<br>
     * no1ppt-{-1}  ：  未经OCR识别，直接返回No1PPT对象
     */
    Map<No1PPT, int[]> selectPPT(No1PPT no1PPT, int minPageNum);

    /**
     * 从 No1PPT 集合 no1PPTCollection 中挑选出幻灯片张数 ≥ minPage 的 No1PPT对象，
     * 以及包含广告的页面index，将这些信息存储于一个Map中，最后将这个mapList返回
     *
     * @param no1PPTCollection No1PPTCollection
     * @param minPageNum       最小幻灯片张数
     * @return key-No1PPT对象，包含广告的页面的index数组，最后返回的是一个MapList<br>
     * no1ppt-int[]  ： 准备生成该PoiPPT对象<br>
     * no1ppt-null  ： 数据库中已经存在对应PoiPPT对象<br>
     * no1ppt-{-1}  ：  未经OCR识别，直接返回No1PPT对象
     */
    List<Map<No1PPT, int[]>> selectPPT(Collection<No1PPT> no1PPTCollection, int minPageNum);

    /**
     * 多线程方式调用 selectPPT(Collection, int)
     *
     * @param no1PPTCollection No1PPTCollection
     * @param minPageNum       最小幻灯片张数
     * @return key-No1PPT对象，包含广告的页面的index数组，最后返回的是一个MapList
     * @see #selectPPT(Collection, int)
     */
    List<Map<No1PPT, int[]>> selectPPTSync(Collection<No1PPT> no1PPTCollection, int minPageNum);

    /**
     * 重建PPT，去掉NoPPT对象对应的PPT文件中的广告页
     *
     * @param no1PPT       需要修改的No1PPT对象
     * @param adPageIndexs 包含广告页面index的数组
     * @return ReturnCode-返回码
     */
    String rebuildPPT(No1PPT no1PPT, int[] adPageIndexs);

    /**
     * 重建PPT，去掉List中所有NoPPT对象对应的PPT文件中的广告页
     *
     * @param infoList 包含 No1PPT-adPageIndexArray 键值对的List
     * @return ReturnCode-返回码
     */
    String rebuildPPT(List<Map<No1PPT, int[]>> infoList);

    /**
     * 使用多线程重建PPT，去掉List中所有NoPPT对象对应的PPT文件中的广告页
     *
     * @param infoList 包含 No1PPT-adPageIndexArray 键值对的List
     * @return ReturnCode-返回码
     */
    String rebuildPPTSync(List<Map<No1PPT, int[]>> infoList);
}
