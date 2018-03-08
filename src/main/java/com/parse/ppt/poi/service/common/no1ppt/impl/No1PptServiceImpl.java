package com.parse.ppt.poi.service.common.no1ppt.impl;

import com.parse.ppt.poi.commom.ReturnCode;
import com.parse.ppt.poi.dao.persistence.No1PptDao;
import com.parse.ppt.poi.entity.No1PPT;
import com.parse.ppt.poi.service.common.no1ppt.No1PptService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jupiter
 */
@Service
public class No1PptServiceImpl implements No1PptService {
    private Logger logger = LogManager.getLogger(this.getClass());
    private final No1PptDao no1PptDao;

    @Autowired
    public No1PptServiceImpl(No1PptDao no1PptDao) {
        this.no1PptDao = no1PptDao;
    }

    @Override
    public List<Map<String, String>> getNo1PPT(int pageIndex, int pageSize) {
        logger.info("No1PptServiceImpl.getNo1PPT()   ------->  start!" +
                "  pageIndex = " + pageIndex +
                "  pageSize = " + pageSize);
        try {
            List<Map<String, String>> resultList = new ArrayList<>();
            List<No1PPT> pptList = no1PptDao.getNo1PPT(pageIndex, pageSize);
            for (No1PPT ppt : pptList) {
                Map<String, String> map = new HashMap<>();
                map.put("id", String.valueOf(ppt.getId()));
                map.put("description", ppt.getSrcDescription());
                map.put("imgUrl", ppt.getSrcImgUrl());
                map.put("downloadPageUrl", ppt.getDownloadPageUrl());
                map.put("downloadUrl", ppt.getDownloadUrl());
                resultList.add(map);
            }
            logger.info("No1PptServiceImpl.getNo1PptByDescription()   ------->  end !");
            return resultList;
        } catch (Exception e) {
            logger.error("No1PptServiceImpl.getNo1PptByDescription()   ------->  ERROR!  返回 null ");
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public String addNo1PPT(No1PPT ppt) {
        try {
            logger.info("No1PptServiceImpl.addNo1PPT()   ------->  start!  ppt = " + ppt);
            no1PptDao.addNo1PPT(ppt);
            logger.info("No1PptServiceImpl.addNo1PPT()   ------->  end ! SUCCESS");
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("No1PptServiceImpl.addNo1PPT()   ------->  ERROR!  返回 FAILED ");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String downloadNo1PPT(int pptId, HttpServletResponse response) {
        try {
            logger.info("No1PptServiceImpl.downloadNo1PPT()   ------->  start!");
            No1PPT pptTemp = no1PptDao.getNo1PPTById(pptId);
            String downloadUrl = pptTemp.getDownloadUrl();
            String downloadPageUrl = pptTemp.getDownloadPageUrl();

            HttpURLConnection conn = (HttpURLConnection) new URL(downloadUrl).openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
            conn.setRequestProperty("referer", downloadPageUrl);
            InputStream inputStream = conn.getInputStream();
            // 创建输出流
            response.setHeader("Content-type", "application-download");
            OutputStream outputStream = response.getOutputStream();
            // 创建缓冲区
            byte[] buffer = new byte[1024];
            int len = 0;
            // 循环将输入流中的内容读取到缓冲区中
            while ((len = inputStream.read(buffer)) > 0) {
                // 输出缓冲区内容到浏览器，实现文件下载
                outputStream.write(buffer, 0, len);
            }
            // 关闭文件流
            inputStream.close();
            // 关闭输出流
            outputStream.close();
            logger.info("No1PptServiceImpl.downloadNo1PPT()   ------->  end ! SUCCESS");
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("No1PptServiceImpl.downloadNo1PPT()   ------->  ERROR!  返回 FAILED ");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }
}
