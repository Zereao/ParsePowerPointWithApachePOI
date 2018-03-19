package com.parse.ppt.poi.service.no1ppt;

import com.parse.ppt.poi.entity.No1PPT;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author Jupiter
 */
@Service
public interface No1PptService {
    /**
     * 数据库分页查询 No1PPT 的信息
     *
     * @param pageIndex 分页-位置偏移量[索引]
     * @param pageSize  分页-需要取得的行数
     * @return 爬取到的 ppt 的信息对象List
     */
    List<Map<String, String>> getNo1PPT(int pageIndex, int pageSize);


    /**
     * 增加 ppt 的信息对象
     *
     * @param ppt 爬取到的 ppt 的信息对象
     * @return 返回码
     */
    String addNo1PPT(No1PPT ppt);

    /**
     * 根据前端传递过来的 pptID，获取到PPT的相关信息
     *
     * @param pptId    数据库中当前PPT的ID
     * @param response HttpServletResponse对象
     * @return 返回码
     */
    String downloadNo1PPT(int pptId, HttpServletResponse response);

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
}
