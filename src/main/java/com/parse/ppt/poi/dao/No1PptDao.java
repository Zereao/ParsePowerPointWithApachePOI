package com.parse.ppt.poi.dao;

import com.parse.ppt.poi.entity.No1PPT;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 使用注解方式配置MyBatis查询
 *
 * @author Jupiter
 * @version 2018/03/05/19:06
 */
@Repository
public interface No1PptDao {
    /**
     * 查出数据库中所有的PPT对象
     *
     * @return No1PPT对象List
     */
    @Select(value = "<script>"
            + "    SELECT id, description, img_url, download_page_url, "
            + "           download_url, file_name, page_num, file_path, "
            + "           thumbnail_path, file_ext "
            + "      FROM no1_ppt"
            + "     WHERE 1=1 "
            + "</script>")
    @Results({
            @Result(column = "id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "description", property = "description", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "img_url", property = "imgUrl", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "download_page_url", property = "downloadPageUrl", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "download_url", property = "downloadUrl", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_name", property = "fileName", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "page_num", property = "pageNum", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(column = "file_path", property = "filePath", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "thumbnail_path", property = "thumbnailPath", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_ext", property = "fileExt", javaType = String.class, jdbcType = JdbcType.VARCHAR)})
    List<No1PPT> getAllNo1Ppts();

    /**
     * 根据pptID 获取到一个No1PPT对象
     *
     * @param no1pptId No1PPT的ID
     * @return No1PPT对象
     */
    @Select(value = "<script>"
            + "    SELECT id, description, img_url, download_page_url, "
            + "           download_url, file_name, page_num, file_path, "
            + "           thumbnail_path, file_ext "
            + "      FROM no1_ppt"
            + "     WHERE 1=1 "
            + "	    AND id = #{no1pptId, jdbcType=INTEGER}"
            + "</script>")
    @Results({
            @Result(column = "id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "description", property = "description", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "img_url", property = "imgUrl", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "download_page_url", property = "downloadPageUrl", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "download_url", property = "downloadUrl", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_name", property = "fileName", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "page_num", property = "pageNum", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(column = "file_path", property = "filePath", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "thumbnail_path", property = "thumbnailPath", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_ext", property = "fileExt", javaType = String.class, jdbcType = JdbcType.VARCHAR)})
    No1PPT getNo1PPTById(@Param("no1pptId") int no1pptId);

    /**
     * 分页查询。
     *
     * @param pageIndex 分页-位置偏移量[索引]-查询开始的索引，从第pageIndex开始
     * @param pageSize  分页-需要取得的行数-需要取得的数据的条数
     * @return 包含No1PPT对象的List
     */
    @Select(value = "<script>"
            + "    SELECT id, description, img_url, download_page_url, "
            + "           download_url, file_name, page_num, file_path, "
            + "           thumbnail_path, file_ext "
            + "      FROM no1_ppt"
            + "     WHERE 1=1 "
            + "	    LIMIT #{pageIndex, jdbcType=INTEGER}, #{pageSize, jdbcType=INTEGER} "
            + "</script>")
    @Results({
            @Result(column = "id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "description", property = "description", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "img_url", property = "imgUrl", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "download_page_url", property = "downloadPageUrl", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "download_url", property = "downloadUrl", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_name", property = "fileName", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "page_num", property = "pageNum", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(column = "file_path", property = "filePath", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "thumbnail_path", property = "thumbnailPath", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_ext", property = "fileExt", javaType = String.class, jdbcType = JdbcType.VARCHAR)})
    List<No1PPT> getNo1PPT(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    /**
     * 根据 keyword 关键词模糊搜索符合条件的No1PPT对象<br>
     * 注意语句 ${keyword}  而不是 #{keyword,jdbcType=INTEGER}
     *
     * @param keyword 关键词，用来模糊搜索符合条件的关键字
     * @return 包含No1PPT对象的List
     */
    @Select(value = "<script>"
            + "    SELECT id, description, img_url, download_page_url, "
            + "           download_url, file_name, page_num, file_path, "
            + "           thumbnail_path, file_ext "
            + "      FROM no1_ppt"
            + "     WHERE 1=1 "
            + "	    AND description like \"%${keyword}%\""
            + "</script>")
    @Results({
            @Result(column = "id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "description", property = "description", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "img_url", property = "imgUrl", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "download_page_url", property = "downloadPageUrl", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "download_url", property = "downloadUrl", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_name", property = "fileName", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "page_num", property = "pageNum", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(column = "file_path", property = "filePath", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "thumbnail_path", property = "thumbnailPath", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_ext", property = "fileExt", javaType = String.class, jdbcType = JdbcType.VARCHAR)})
    List<No1PPT> getNo1PPTByKeyWordFuzzy(@Param("keyword") String keyword);

    /**
     * 根据多个 keyword 关键词模糊搜索符合条件的No1PPT对象
     *
     * @param keywordsList 包含关键词的List
     * @return 包含No1PPT对象的List
     */
    @Select(value = "<script>"
            + "    SELECT id, description, img_url, download_page_url, "
            + "           download_url, file_name, page_num, file_path, "
            + "           thumbnail_path, file_ext "
            + "      FROM no1_ppt"
            + "     WHERE 1=0 "
            + "     <foreach collection=\"keywordsList\" item=\"keyword\" index=\"index\" open=\"\" close=\"\" separator=\"\"> "
            + "          or description like \"%${keyword}%\""
            + "     </foreach>  "
            + "</script>")
    @Results({
            @Result(column = "id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "description", property = "description", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "img_url", property = "imgUrl", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "download_page_url", property = "downloadPageUrl", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "download_url", property = "downloadUrl", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_name", property = "fileName", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "page_num", property = "pageNum", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(column = "file_path", property = "filePath", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "thumbnail_path", property = "thumbnailPath", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_ext", property = "fileExt", javaType = String.class, jdbcType = JdbcType.VARCHAR)})
    List<No1PPT> getNo1PPTByKeyWordsFuzzy(@Param("keywordsList") List<String> keywordsList);

    /**
     * 精确搜索同时包含keywordsList中所有关键词的No1PPT
     *
     * @param keywordsList 包含关键词的List
     * @return 同时包含keywordsList中所有关键词的No1PPTList
     */
    @Select(value = "<script>"
            + "    SELECT id, description, img_url, download_page_url, "
            + "           download_url, file_name, page_num, file_path, "
            + "           thumbnail_path, file_ext "
            + "      FROM no1_ppt"
            + "     WHERE 1=1 "
            + "     <foreach collection=\"keywordsList\" item=\"keyword\" index=\"index\" open=\"\" close=\"\" separator=\"\"> "
            + "          and description like \"%${keyword}%\""
            + "     </foreach>  "
            + "</script>")
    @Results({
            @Result(column = "id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "description", property = "description", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "img_url", property = "imgUrl", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "download_page_url", property = "downloadPageUrl", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "download_url", property = "downloadUrl", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_name", property = "fileName", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "page_num", property = "pageNum", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(column = "file_path", property = "filePath", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "thumbnail_path", property = "thumbnailPath", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_ext", property = "fileExt", javaType = String.class, jdbcType = JdbcType.VARCHAR)})
    List<No1PPT> getNo1PPTByKeyWordsExact(@Param("keywordsList") List<String> keywordsList);

    /**
     * 增加 ppt 的信息对象
     *
     * @param ppt 爬取到的 ppt 的信息对象
     */
    @Insert(value = "<script>"
            + "    INSERT INTO no1_ppt (description, img_url, download_page_url, download_url, file_name, "
            + "                         page_num, file_path, thumbnail_path, file_ext)"
            + "         VALUES (#{description, jdbcType=VARCHAR}, #{imgUrl, jdbcType=VARCHAR},"
            + "                 #{downloadPageUrl, jdbcType=VARCHAR}, #{downloadUrl, jdbcType=VARCHAR},"
            + "                 #{fileName, jdbcType=VARCHAR}, #{pageNum, jdbcType=INTEGER},"
            + "                 #{filePath, jdbcType=VARCHAR}, #{thumbnailPath, jdbcType=VARCHAR},"
            + "                 #{fileExt, jdbcType=VARCHAR})"
            + "</script>")
    void addNo1PPT(No1PPT ppt);

    /**
     * 更新No1PPT的信息——修改压缩包中的No1PPT文件的文件名
     *
     * @param no1pptId No1PPT的ID
     * @param fileName No1PPT文件的文件名
     */
    @Update(value = "<script>" +
            "   UPDATE no1_ppt" +
            "       <set>" +
            "           <if test=\"fileName != null\">" +
            "               file_name = #{fileName, jdbcType=VARCHAR}," +
            "           </if>" +
            "       </set>" +
            "   WHERE id = #{no1pptId, jdbcType=INTEGER}" +
            "</script>")
    void updateNo1PPTFileName(@Param("no1pptId") int no1pptId,
                              @Param("fileName") String fileName);

}
