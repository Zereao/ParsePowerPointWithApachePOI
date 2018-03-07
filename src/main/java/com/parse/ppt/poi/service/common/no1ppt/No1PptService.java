package com.parse.ppt.poi.service.common.no1ppt;

import com.parse.ppt.poi.entity.No1PPT;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public interface No1PptService {
    /**
     * 通过email获取用户信息
     *
     * @param pageIndex 分页-位置偏移量[索引]
     * @param pageSize  分页-需要取得的行数
     * @return 爬取到的 ppt 的信息对象
     */
    JSONArray getNo1PPT(int pageIndex, int pageSize);


    /**
     * 增加 ppt 的信息对象
     *
     * @param ppt 爬取到的 ppt 的信息对象
     * @return 返回码
     */
    String addNo1PPT(No1PPT ppt);

    /**
     * 根据前端传递过来的 下载页的链接 和 下载链接
     *
     * @param downloadPageUrl 下载页面的链接，用来 做 referer 欺骗
     * @param downloadUrl     下载链接
     * @return 返回码
     */
    String downloadNo1PPT(int pptId, HttpServletResponse response);
}
