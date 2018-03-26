package com.parse.ppt.poi.dao.persistence;

import com.parse.ppt.poi.entity.No1PPT;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
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
     * 根据pptID 获取到一个No1PPT对象
     *
     * @param no1pptId No1PPT的ID
     * @return No1PPT对象
     */
    @Select(value = "<script>"
            + "    SELECT *"
            + "      FROM no_1_ppt"
            + "     WHERE 1=1 "
            + "	    AND id = #{no1pptId, jdbcType=INTEGER}"
            + "</script>")
    @ResultMap("com.parse.ppt.poi.dao.persistence.No1PptDao.BaseResultMap")
    No1PPT getNo1PPTById(@Param("no1pptId") int no1pptId);

    /**
     * 分页查询。
     *
     * @param pageIndex 分页-位置偏移量[索引]-查询开始的索引，从第pageIndex开始
     * @param pageSize  分页-需要取得的行数-需要取得的数据的条数
     * @return 包含No1PPT对象的List
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
     * 根据 keyword 关键词模糊搜索符合条件的No1PPT对象<br>
     * 注意语句 ${keyword}  而不是 #{keyword,jdbcType=INTEGER}
     *
     * @param keyword 关键词，用来模糊搜索符合条件的关键字
     * @return 包含No1PPT对象的List
     */
    @Select(value = "<script>"
            + "    SELECT *"
            + "      FROM no_1_ppt"
            + "     WHERE 1=1 "
            + "	    AND description like \"%${keyword}%\""
            + "</script>")
    @ResultMap("com.parse.ppt.poi.dao.persistence.No1PptDao.BaseResultMap")
    List<No1PPT> getNo1PPTByKeyWordFuzzy(@Param("keyword") String keyword);

    /**
     * 根据多个 keyword 关键词模糊搜索符合条件的No1PPT对象
     *
     * @param keywordsList 包含关键词的List
     * @return 包含No1PPT对象的List
     */
    @Select(value = "<script>"
            + "    SELECT *"
            + "      FROM no_1_ppt"
            + "     WHERE 1=0 "
            + "     <foreach collection=\"keywordsList\" item=\"keyword\" index=\"index\" open=\"\" close=\"\" separator=\"\"> "
            + "          or description like \"%${keyword}%\""
            + "     </foreach>  "
            + "</script>")
    @ResultMap("com.parse.ppt.poi.dao.persistence.No1PptDao.BaseResultMap")
    List<No1PPT> getNo1PPTByKeyWordsFuzzy(@Param("keywordsList") List<String> keywordsList);

    /**
     * 精确搜索同时包含keywordsList中所有关键词的No1PPT
     *
     * @param keywordsList 包含关键词的List
     * @return 同时包含keywordsList中所有关键词的No1PPTList
     */
    @Select(value = "<script>"
            + "    SELECT *"
            + "      FROM no_1_ppt"
            + "     WHERE 1=1 "
            + "     <foreach collection=\"keywordsList\" item=\"keyword\" index=\"index\" open=\"\" close=\"\" separator=\"\"> "
            + "          and description like \"%${keyword}%\""
            + "     </foreach>  "
            + "</script>")
    @ResultMap("com.parse.ppt.poi.dao.persistence.No1PptDao.BaseResultMap")
    List<No1PPT> getNo1PPTByKeyWordsExact(@Param("keywordsList") List<String> keywordsList);

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
     * 更新No1PPT的信息——修改压缩包中的No1PPT文件的文件名
     *
     * @param no1pptId         No1PPT的ID
     * @param no1pptIdFileName 压缩包中的No1PPT文件的文件名
     */
    void updateNo1PPTFileName(@Param("no1pptId") int no1pptId,
                              @Param("no1pptIdFileName") String no1pptIdFileName);

}
