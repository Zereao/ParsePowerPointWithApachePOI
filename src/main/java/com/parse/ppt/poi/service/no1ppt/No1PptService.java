package com.parse.ppt.poi.service.no1ppt;

import com.parse.ppt.poi.entity.No1PPT;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jupiter
 */
@Service
public interface No1PptService {
    /**
     * 根据No1PPT的ID查询 No1PPT 的信息
     *
     * @param no1PptId No1PPT对象的ID
     * @return 对应的No1PPT对象
     */
    No1PPT getNo1PptById(String no1PptId);

    /**
     * 数据库分页查询 No1PPT 的信息
     *
     * @param pageIndex 分页-位置偏移量[索引]
     * @param pageSize  分页-需要取得的行数
     * @return 包含ppt信息Json对象的JsonArray
     */
    JSONArray getNo1PPT(int pageIndex, int pageSize);

    /**
     * 获取40页 No1PPT 的信息
     *
     * @param pageIndex 分页-位置偏移量[索引]
     * @return 包含 ppt信息Json对象的JsonArray
     */
    JSONArray getNo1PptWithSize40(int pageIndex);

    /**
     * 增加 ppt 的信息对象
     *
     * @param ppt 爬取到的 ppt 的信息对象
     * @return 返回码
     */
    String addNo1PPT(No1PPT ppt);

    /**
     * 更新No1PPT的信息——修改压缩包中的PPT文件的文件名
     *
     * @param no1PptId    No1PPT的ID
     * @param pptFileName 压缩包中的PPT文件的文件名
     * @return ReturnCode - 返回码
     */
    String updateNo1PPTFileName(String no1PptId, String pptFileName);

    /**
     * 根据前端传递过来的 no1PptID，获取到PPT的相关信息
     *
     * @param no1PptID 数据库中当前no1PptID的ID
     * @param request  HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @return 返回码
     */
    String downloadNo1PPT(String no1PptID, HttpServletRequest request, HttpServletResponse response);

    /**
     * 把No1PPT - PPT文件转换为PNG格式的图片，并且将其存储于 pptFile 所在的路径下的 PPT2IMG 路径下
     *
     * @param no1PptID NO1PPT的ID，对应的PPT文件可以是.PPT格式的，也可以是.PPTX格式的
     * @return ReturnCode-返回码
     */
    String ppt2img(String no1PptID);

    /**
     * 获取某一个PPT转换成图片的图片张数
     *
     * @param no1PptId No1PPTd的ID
     * @return 某一个PPT转换成图片的图片张数
     */
    int getImgsNum(String no1PptId);

}
