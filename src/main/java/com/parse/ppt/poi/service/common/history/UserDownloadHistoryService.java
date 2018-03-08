package com.parse.ppt.poi.service.common.history;

import com.parse.ppt.poi.entity.No1PPT;
import com.parse.ppt.poi.entity.UserDownloadHistory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jupiter
 * @date 2018/03/08/18:32
 */
@Service
public interface UserDownloadHistoryService {
    /**
     * 通过email获取用户的No1PPT的下载历史
     *
     * @param email 用户电子邮箱
     * @return 包含No1PPT的List
     */
    List<No1PPT> getHistoryByEmail(String email);

    /**
     * 增加用户的No1PPT的下载历史
     *
     * @param userDownloadHistory 增加的用户的No1PPT的下载历史
     */
    String addDownloadHistory(UserDownloadHistory userDownloadHistory);
}
