package com.parse.ppt.poi.service.download;

import com.parse.ppt.poi.entity.No1PPT;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author Jupiter
 * @date 2018/03/08/16:46
 */
@Service
public interface FileDownloadService {
    /**
     * 获取40页的 No1PPT 的信息
     *
     * @param pageIndex 分页-位置偏移量[索引]
     * @return 爬取到的 ppt 的信息对象 的jsonArray
     */
    JSONArray getNo1PPT(int pageIndex);

    /**
     * 根据前端传递过来的 pptID，获取到PPT的相关信息
     *
     * @param pptId    数据库中当前PPT的ID
     * @param response HttpServletResponse对象
     * @return 返回码
     */
    String downloadNo1PPT(int pptId, HttpServletResponse response);
}
