package com.parse.ppt.poi.dao.persistence;

import com.parse.ppt.poi.entity.No1PPT;
import org.springframework.stereotype.Repository;

/**
 * @author Jupiter
 * @date 2018/03/05/19:06
 */
@Repository
public interface No1PptDao {
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
     */
    void addNo1PPT(No1PPT ppt);

}
