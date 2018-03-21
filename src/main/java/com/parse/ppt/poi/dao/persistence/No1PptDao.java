package com.parse.ppt.poi.dao.persistence;

import com.parse.ppt.poi.entity.No1PPT;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 使用注解方式配置MyBatis查询
 *
 * @author Jupiter
 * @date 2018/03/05/19:06
 */
@Repository
public interface No1PptDao {
    /**
     * 分页查询。
     *
     * @param pageIndex 分页-位置偏移量[索引]-查询开始的索引，从第pageIndex开始
     * @param pageSize  分页-需要取得的行数-需要取得的数据的条数
     * @return PPT信息对象List
     */
    @Select(value = "<script>"
            + "    SELECT *"
            + "      FROM no_1_ppt"
            + "     WHERE 1=1 "
            + "	    LIMIT #{pageIndex, jdbcType=INTEGER}, #{pageSize, jdbcType=INTEGER} "
            + "</script>")
    @ResultMap("com.parse.ppt.poi.dao.persistence.No1PptDao.BaseResultMap")
    List<No1PPT> getNo1PPT(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    /**
     * 根据pptID 获取到一个No1PPT对象
     *
     * @param pptId No1PPT的ID
     * @return PPT信息对象List
     */
    @Select(value = "<script>"
            + "    SELECT *"
            + "      FROM no_1_ppt"
            + "     WHERE 1=1 "
            + "	    AND id = #{pptId, jdbcType=INTEGER}"
            + "</script>")
    @ResultMap("com.parse.ppt.poi.dao.persistence.No1PptDao.BaseResultMap")
    No1PPT getNo1PPTById(@Param("pptId") int pptId);

    /**
     * 增加 ppt 的信息对象
     *
     * @param ppt 爬取到的 ppt 的信息对象
     */
    void addNo1PPT(No1PPT ppt);

    /**
     * 更新No1PPT 的信息
     *
     * @param no1PPT No1PPT对象
     */
    void updateNo1PPT(No1PPT no1PPT);

    /**
     * 更新No1PPT的信息——修改压缩包中的PPT文件的文件名
     *
     * @param pptId       No1PPT的ID
     * @param pptFileName 压缩包中的PPT文件的文件名
     */
    void updateNo1PPTFileName(@Param("pptId") int pptId,
                              @Param("pptFileName") String pptFileName);

}
