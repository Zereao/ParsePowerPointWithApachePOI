package com.parse.ppt.poi.dao.persistence;

import com.parse.ppt.poi.entity.No1PPT;
import com.parse.ppt.poi.entity.UserDownloadHistory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Jupiter
 * @date 2018/03/08/18:13
 */
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
    @ResultMap("com.parse.ppt.poi.dao.persistence.UserDownloadHistoryDao.BaseResultMap")
    List<No1PPT> getHistoryByEmail(String email);

    /**
     * 增加用户的No1PPT的下载历史
     *
     * @param userDownloadHistory 增加的用户的No1PPT的下载历史
     */
    @Insert(value = " INSERT INTO user_download_history ( email, ppt_id ) "
            + "        VALUES ( #{email, jdbcType=VARCHAR}, #{pptId, jdbcType=INTEGER})")
    void addDownloadHistory(UserDownloadHistory userDownloadHistory);
}
