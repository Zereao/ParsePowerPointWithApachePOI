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
     * @param pptId No1PPT对象的ID
     * @return 对应的No1PPT对象
     */
    No1PPT getNo1PptById(String pptId);

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
     * @param pptId       No1PPT的ID
     * @param pptFileName 压缩包中的PPT文件的文件名
     * @return ReturnCode - 返回码
     */
    String updateNo1PPTFileName(String pptId, String pptFileName);

    /**
     * 根据前端传递过来的 pptID，获取到PPT的相关信息
     *
     * @param pptId    数据库中当前PPT的ID
     * @param request  HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @return 返回码
     */
    String downloadNo1PPT(int pptId, HttpServletRequest request, HttpServletResponse response);

    /**
     * 测试方法中执行的方法，批量把 pages页的PPT下载到本地
     *
     * @param pages 需要下载的总页数，每一页含有40张PPT
     * @return 返回码
     */
    String downloadAllNo1PPT(int pages);

    /**
     * 测试方法中执行的方法，开启5个线程，每个线程下载340条No1PPT到本地，共计1700条信息
     *
     * @return 返回码
     */
    String downloadAllNo1PPTSync();

    /**
     * 获取某一个PPT转换成图片的图片张数
     *
     * @param pptId No1PPTd的ID
     * @return 某一个PPT转换成图片的图片张数
     */
    int getImgsNum(String pptId);

}
