package com.parse.ppt.poi.service;

import com.parse.ppt.poi.entity.No1PPT;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public interface PoiPptService {

    /**
     * 根据关键词，按照关联程度获取到对应的PoiPPT对象List<br>
     * 如果得到的数据超过80条，则只获取80条数据
     *
     * @param keywords 关键词，以逗号隔开
     * @param session  HttpSession
     * @return 对应的PoiPPT对象List
     */
    List<No1PPT> getNo1PptByKeyword(String keywords, HttpSession session);

    /**
     * 根据关键词，按照关联程度获取到对应的PoiPPT对象List<br>
     * 如果得到的数据超过80条，则只获取80条数据<br>
     * 该方法用于向前端返回数据
     *
     * @param no1PPTList 查询得到的No1PPTList
     * @return 对应的PoiPPT对象List
     */
    JSONArray getPoiPPTofMax80(List<No1PPT> no1PPTList);

    /**
     * 处理某一个No1PPT对象，对某一个no1pptId 对应的 No1PPT 进行 删选、图像识别、去广告页等处理。
     *
     * @param no1pptId PoiPPT对象对应No1PPT对象的ID
     * @return ReturnCode返回码
     */
    String operateForPoiPpt(String no1pptId);

    /**
     * 处理之前剩下的未处理的PoiPPTList——从session从获取 remainList属性得到<br>
     * 为了保证用户体验只读取16条
     *
     * @param session HttpSession对象
     * @return ReturnCode返回码
     */
    String operateForRemainPoiPptList(HttpSession session);

    /**
     * 获取16条PoiPPT的数据，同时再处理16条数据
     *
     * @param keywords 关键词
     * @param session  HttpSession对象
     * @return 包含了PoiPPT信息的JsonArray对象
     */
    JSONArray getSearchResult(String keywords, HttpSession session);

    /**
     * 把PoiPPT - PPT文件转换为PNG格式的图片，并且将其存储于 /ZeroFilesOutput/ppt2imgs/${PptTags}/ 路径下
     *
     * @param poipptId PoiPPT的ID，对应的PPT文件可以是.PPT格式的，也可以是.PPTX格式的
     * @return ReturnCode-返回码
     */
    String ppt2img(String poipptId);

    /**
     * 获取某一个PPT转换成图片的图片张数
     *
     * @param poipptId poippt的ID
     * @return 某一个PPT转换成图片的图片张数
     */
    int getImgsNum(String poipptId);

    /**
     * 处理PPT，根据得到的No1PPTID，对对应PoiPPT进行 转图片、筛选处理，并返回转换后的图片张数
     *
     * @param no1pptId Poippt对应的No1PPT的ID
     * @return 某一个PPT转换成图片的图片张数
     */
    int OperatePPT(String no1pptId);
}
