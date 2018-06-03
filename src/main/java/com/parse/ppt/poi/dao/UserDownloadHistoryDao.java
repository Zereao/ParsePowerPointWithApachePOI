package com.parse.ppt.poi.dao;

import com.parse.ppt.poi.entity.UserDownloadHistory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jupiter
 * @version 2018/03/08/18:13
 */
@Repository
public interface UserDownloadHistoryDao {
    /**
     * 通过email获取用户的No1PPT的下载历史
     *
     * @param email 用户电子邮箱
     * @return 包含No1PPT的List
     */
    @Select(value = "<script>"
            + "    SELECT *"
            + "      FROM user_download_history"
            + "     WHERE 1=1 "
            + "	    LIMIT #{pageIndex, jdbcType=INTEGER}, #{pageSize, jdbcType=INTEGER} "
            + "</script>")
    @Results({
            @Result(column = "id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "email", property = "email", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "ppt_id", property = "pptId", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(column = "ppt_type", property = "pptType", javaType = String.class, jdbcType = JdbcType.VARCHAR)})
    List<UserDownloadHistory> getHistoryByEmail(String email);

    /**
     * 增加用户的No1PPT的下载历史
     *
     * @param userDownloadHistory 增加的用户的No1PPT的下载历史
     */
    @Insert(value = " INSERT INTO user_download_history ( email, ppt_id ) "
            + "        VALUES ( #{email, jdbcType=VARCHAR}, #{pptId, jdbcType=INTEGER})")
    void addDownloadHistory(UserDownloadHistory userDownloadHistory);
}
