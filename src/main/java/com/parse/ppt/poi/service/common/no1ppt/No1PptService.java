package com.parse.ppt.poi.service.common.no1ppt;

import com.parse.ppt.poi.entity.No1PPT;
import org.springframework.stereotype.Service;

@Service
public interface No1PptService {
    /**
     * 通过email获取用户信息
     *
     * @param srcDescription ppt描述信息
     * @return 爬取到的 ppt 的信息对象
     */
    No1PPT getNo1PptByDescription(String srcDescription);


    /**
     * 增加 ppt 的信息对象
     *
     * @param ppt 爬取到的 ppt 的信息对象
     * @return 返回码
     */
    String addNo1PPT(No1PPT ppt);
}
