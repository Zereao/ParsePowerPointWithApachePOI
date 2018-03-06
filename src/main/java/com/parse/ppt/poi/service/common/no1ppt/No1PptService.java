package com.parse.ppt.poi.service.common.no1ppt;

import com.parse.ppt.poi.entity.No1PPT;
import org.springframework.stereotype.Service;

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
    List<No1PPT> getNo1PPT(int pageIndex, int pageSize);


    /**
     * 增加 ppt 的信息对象
     *
     * @param ppt 爬取到的 ppt 的信息对象
     * @return 返回码
     */
    String addNo1PPT(No1PPT ppt);
}
