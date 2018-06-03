package com.parse.ppt.poi.service;

import com.parse.ppt.poi.entity.No1PPT;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/**
 * @author Jupiter
 * @version 2018/05/29 14:38
 */
@Service
public interface No1PptService {

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
     * 根据前端传递过来的 no1PptID，获取到PPT的相关信息
     *
     * @param no1PptID 数据库中当前no1PptID的ID
     * @param request  HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @return 返回码
     */
    String downloadNo1PPT(String no1PptID, HttpServletRequest request, HttpServletResponse response);

    /**
     * 把No1PPT - PPT文件转换为PNG格式的图片，并且将其存储于 /ZeroFilesOutput/ppt2imgs/no1ppt2imgs 路径下
     *
     * @param no1PptID NO1PPT的ID，对应的PPT文件可以是.PPT格式的，也可以是.PPTX格式的
     * @return ReturnCode-返回码
     */
    String ppt2img(String no1PptID);

    /**
     * 获取某一个PPT转换成图片的图片张数
     *
     * @param no1PptId No1PPT的ID
     * @return 某一个PPT转换成图片的图片张数
     */
    int getImgsNum(String no1PptId);

}
