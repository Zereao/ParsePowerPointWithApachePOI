package com.parse.ppt.poi.service.poippt;

import com.parse.ppt.poi.entity.No1PPT;
import org.springframework.stereotype.Service;

@Service
public interface PoiPptService {
    /**
     * 获取到关键词关联度从高到低的No1PPT，然后筛选出 PPT页面数大于5的No1PPT
     *
     * @param no1PptId No1PPT对象的ID
     * @return 对应的No1PPT对象
     */
    No1PPT getNo1PptById(String no1PptId);
}
