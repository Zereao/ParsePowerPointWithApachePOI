package com.parse.ppt.poi.service.download.impl;

import com.parse.ppt.poi.commom.ReturnCode;
import com.parse.ppt.poi.entity.User;
import com.parse.ppt.poi.entity.UserDownloadHistory;
import com.parse.ppt.poi.service.common.history.UserDownloadHistoryService;
import com.parse.ppt.poi.service.common.no1ppt.No1PptService;
import com.parse.ppt.poi.service.download.FileDownloadService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author Jupiter
 * @date 2018/03/08/16:45
 */
@Service
public class FileDownloadServiceImpl implements FileDownloadService {
    private Logger logger = LogManager.getLogger(this.getClass());

    private final No1PptService no1PptService;
    private final UserDownloadHistoryService userDownloadHistoryService;

    @Autowired
    public FileDownloadServiceImpl(No1PptService no1PptService, UserDownloadHistoryService userDownloadHistoryService) {
        this.no1PptService = no1PptService;
        this.userDownloadHistoryService = userDownloadHistoryService;
    }

    @Override
    public JSONArray getNo1PPT(int pageIndex) {
        logger.info("FileDownloadServiceImpl.getNo1PPT   ------->  start!" +
                "   pageIndex = " + pageIndex);
        try {
            List<Map<String, String>> resultMapList = no1PptService.getNo1PPT(pageIndex, 40);
            JSONArray jsonArray = new JSONArray();
            for (Map<String, String> map : resultMapList) {
                JSONObject jsonObject = new JSONObject();
                for (String key : map.keySet()) {
                    jsonObject.put(key, map.get(key));
                }
                jsonArray.add(jsonObject);
            }
            logger.info("FileDownloadServiceImpl.getNo1PPT   ------->  end!  SUCCESS");
            return jsonArray;
        } catch (Exception e) {
            logger.error("FileDownloadServiceImpl.getNo1PPT   ------->  ERROR!  返回 FAILED ");
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public String downloadNo1PPT(int pptId, HttpSession session, HttpServletResponse response) {
        logger.info("FileDownloadServiceImpl.downloadNo1PPT   ------->  start!" +
                "   pptId = " + pptId);
        try {
            String result = no1PptService.downloadNo1PPT(pptId, response);
            boolean addUserDownloadHistory = result.equals(ReturnCode.SUCCESS) && session.getAttribute("user") != null;
            if (addUserDownloadHistory) {
                User user = (User) session.getAttribute("user");
                UserDownloadHistory userDownloadHistory = new UserDownloadHistory(user.getEmail(), pptId);
                result = userDownloadHistoryService.addDownloadHistory(userDownloadHistory);
            }
            logger.info("FileDownloadServiceImpl.downloadNo1PPT   ------->  end!  result = " + result);
            return result;
        } catch (Exception e) {
            logger.error("FileDownloadServiceImpl.downloadNo1PPT   ------->  ERROR!  返回 FAILED ");
            logger.error(e.getMessage());
        }
        return null;
    }
}
