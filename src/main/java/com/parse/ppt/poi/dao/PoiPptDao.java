package com.parse.ppt.poi.dao;

import com.parse.ppt.poi.entity.PoiPPT;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jupiter
 * @version 2018/3/25/10:41
 */
@Repository
public interface PoiPptDao {
    /**
     * 分页查询。
     *
     * @param pageIndex 分页-位置偏移量[索引]-查询开始的索引，从第pageIndex开始
     * @param pageSize  分页-需要取得的行数-需要取得的数据的条数
     * @return PPT信息对象List
     */
    @Select(value = "<script>"
            + "    SELECT *"
            + "      FROM poi_ppt"
            + "     WHERE 1=1 "
            + "	    LIMIT #{pageIndex, jdbcType=INTEGER}, #{pageSize, jdbcType=INTEGER} "
            + "</script>")
    @Results({
            @Result(column = "id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "description", property = "pptDescription", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "type_tag", property = "typeTag", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "no1_1_ppt_id", property = "no1pptId", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(column = "img_address", property = "pptImgAddress", javaType = String.class, jdbcType = JdbcType.INTEGER),
            @Result(column = "download_url", property = "pptDownloadUrl", javaType = String.class, jdbcType = JdbcType.INTEGER),
            @Result(column = "ppt_file_name", property = "pptFileName", javaType = String.class, jdbcType = JdbcType.VARCHAR)})
    List<PoiPPT> getPoiPPT(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    /*  property  属性，映射的是对应实体类中的属性   */

    /**
     * 根据poiPptId 获取到一个PoiPPT对象
     *
     * @param poiPptId PoiPPT的ID
     * @return PoiPPT对象
     */
    @Select(value = "<script>"
            + "    SELECT *"
            + "      FROM poi_ppt"
            + "     WHERE 1=1 "
            + "	    AND id = #{poiPptId, jdbcType=INTEGER}"
            + "</script>")
    @Results({
            @Result(column = "id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "description", property = "pptDescription", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "type_tag", property = "typeTag", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "no1_1_ppt_id", property = "no1pptId", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(column = "img_address", property = "pptImgAddress", javaType = String.class, jdbcType = JdbcType.INTEGER),
            @Result(column = "download_url", property = "pptDownloadUrl", javaType = String.class, jdbcType = JdbcType.INTEGER),
            @Result(column = "ppt_file_name", property = "pptFileName", javaType = String.class, jdbcType = JdbcType.VARCHAR)})
    PoiPPT getPoiPPTById(@Param("poiPptId") int poiPptId);

    /**
     * 根据no1pptID 获取到一个PoiPPT对象
     *
     * @param no1pptId PoiPPT的no1pptId
     * @return PoiPPT对象
     */
    @Select(value = "<script>"
            + "    SELECT *"
            + "      FROM poi_ppt"
            + "     WHERE 1=1 "
            + "	    AND no_1_ppt_id = #{no1pptId, jdbcType=INTEGER}"
            + "</script>")
    @Results({
            @Result(column = "id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "description", property = "pptDescription", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "type_tag", property = "typeTag", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "no1_1_ppt_id", property = "no1pptId", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(column = "img_address", property = "pptImgAddress", javaType = String.class, jdbcType = JdbcType.INTEGER),
            @Result(column = "download_url", property = "pptDownloadUrl", javaType = String.class, jdbcType = JdbcType.INTEGER),
            @Result(column = "ppt_file_name", property = "pptFileName", javaType = String.class, jdbcType = JdbcType.VARCHAR)})
    PoiPPT getPoiPPTByNo1pptId(@Param("no1pptId") int no1pptId);

    /**
     * 增加PoiPPT对象
     *
     * @param poiPPT PoiPPT对象
     */
    @Insert("insert into poi_ppt (description, type_tag, no_1_ppt_id)" +
            "values ( #{pptDescription,jdbcType=VARCHAR}, " +
            " #{typeTag,jdbcType=VARCHAR}," +
            " #{no1pptId,jdbcType=INTEGER})")
    void addPoiPPT(PoiPPT poiPPT);

    /**
     * 更新PoiPPT的信息——修改压缩包中的PPT文件的文件名
     *
     * @param poiPptId       PoiPPT的ID
     * @param poiPptFileName 压缩包中的PPT文件的文件名
     */
    @Update(value = "<script>" +
            "update poi_ppt " +
            "   <set>" +
            "      <if test=\"poiPptFileName != null\">" +
            "          ppt_file_name = #{poiPptFileName,jdbcType=VARCHAR}," +
            "      </if>" +
            "   </set>" +
            "      where id = #{poiPptId,jdbcType=INTEGER}" +
            "</script>")
    void updatePoiPptFileName(@Param("poiPptId") int poiPptId,
                              @Param("poiPptFileName") String poiPptFileName);
}
