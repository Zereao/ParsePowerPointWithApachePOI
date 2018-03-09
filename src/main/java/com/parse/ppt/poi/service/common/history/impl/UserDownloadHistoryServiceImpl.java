package com.parse.ppt.poi.service.common.history.impl;

import com.parse.ppt.poi.common.ReturnCode;
import com.parse.ppt.poi.dao.persistence.UserDownloadHistoryDao;
import com.parse.ppt.poi.entity.No1PPT;
import com.parse.ppt.poi.entity.UserDownloadHistory;
import com.parse.ppt.poi.service.common.history.UserDownloadHistoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jupiter
 * @date 2018/03/08/18:32
 */
@Service
public class UserDownloadHistoryServiceImpl implements UserDownloadHistoryService {
    private Logger logger = LogManager.getLogger(this.getClass());

    private final UserDownloadHistoryDao userDownloadHistoryDao;

    @Autowired
    public UserDownloadHistoryServiceImpl(UserDownloadHistoryDao userDownloadHistoryDao) {
        this.userDownloadHistoryDao = userDownloadHistoryDao;
    }

    @Override
    public List<No1PPT> getHistoryByEmail(String email) {
        try {
            logger.info("------->  start!  email = " + email);
            List<No1PPT> pptList = userDownloadHistoryDao.getHistoryByEmail(email);
            logger.info("------->  end!   pptList = " + pptList);
            return pptList;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 null ");
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public String addDownloadHistory(UserDownloadHistory userDownloadHistory) {
        try {
            logger.info("------->  start!  userDownloadHistory = " + userDownloadHistory);
            userDownloadHistoryDao.addDownloadHistory(userDownloadHistory);
            logger.info("------->  end  SUCCESS");
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 null ");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }
}
