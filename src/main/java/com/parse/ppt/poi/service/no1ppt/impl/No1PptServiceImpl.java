package com.parse.ppt.poi.service.no1ppt.impl;

import com.parse.ppt.poi.common.ReturnCode;
import com.parse.ppt.poi.dao.persistence.No1PptDao;
import com.parse.ppt.poi.entity.No1PPT;
import com.parse.ppt.poi.service.no1ppt.No1PptService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
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
        logger.info("------->  start!" +
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
            logger.info("------->  end !");
            return resultList;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 null ");
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public String addNo1PPT(No1PPT ppt) {
        try {
            logger.info("------->  start!  ppt = " + ppt);
            no1PptDao.addNo1PPT(ppt);
            logger.info("------->  end ! SUCCESS");
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 FAILED ");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String downloadNo1PPT(int pptId, HttpServletResponse response) {
        try {
            logger.info("------->  start!");
            No1PPT pptTemp = no1PptDao.getNo1PPTById(pptId);
            String downloadUrl = pptTemp.getDownloadUrl();
            String downloadPageUrl = pptTemp.getDownloadPageUrl();

            HttpURLConnection conn = (HttpURLConnection) new URL(downloadUrl).openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
            conn.setRequestProperty("referer", downloadPageUrl);
            InputStream inputStream = conn.getInputStream();
            // 创建输出流
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
            logger.info("------->  end ! SUCCESS");
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 FAILED ");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String downloadAllNo1PPT(int pages) {
//        已知数据库中存在1720条数据
        try {
            logger.info("------->  start!");
            List<No1PPT> no1PPTList = no1PptDao.getNo1PPT(0, pages * 40);
            final String BASE_PATH = "文件输出/NO1PPTS/";
            // 如果文件夹不存在，则创建
            File tempFile = new File(BASE_PATH);
            if (!tempFile.exists()) {
                boolean isCreate = tempFile.mkdir();
            }
            downloadNo1PPT(no1PPTList, BASE_PATH);
            logger.info("------->  end ! SUCCESS");
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 FAILED ");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String downloadAllNo1PPTSync() {
        // 已知数据库中存在1720条数据，这里只下载1700条
        try {
            logger.info("------->  start!");
            List<No1PPT> no1PPTList1 = no1PptDao.getNo1PPT(0, 340);
            List<No1PPT> no1PPTList2 = no1PptDao.getNo1PPT(340, 340 * 2);
            List<No1PPT> no1PPTList3 = no1PptDao.getNo1PPT(340 * 2, 340 * 3);
            List<No1PPT> no1PPTList4 = no1PptDao.getNo1PPT(340 * 3, 340 * 4);
            List<No1PPT> no1PPTList5 = no1PptDao.getNo1PPT(340 * 4, 340 * 5);
            final String BASE_PATH = "文件输出/NO1PPTS/";
            // 如果文件夹不存在，则创建
            File tempFile = new File(BASE_PATH);
            if (!tempFile.exists()) {
                boolean isCreate = tempFile.mkdir();
            }
            Thread thread1 = new Thread(() -> downloadNo1PPT(no1PPTList1, BASE_PATH));
            Thread thread2 = new Thread(() -> downloadNo1PPT(no1PPTList2, BASE_PATH));
            Thread thread3 = new Thread(() -> downloadNo1PPT(no1PPTList3, BASE_PATH));
            Thread thread4 = new Thread(() -> downloadNo1PPT(no1PPTList4, BASE_PATH));
            Thread thread5 = new Thread(() -> downloadNo1PPT(no1PPTList5, BASE_PATH));
            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
            thread5.start();
            logger.info("------->  end ! SUCCESS");
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 FAILED ");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }


    /**
     * 私有的No1PPT下载方法，实现了Referer欺骗
     *
     * @param no1PPTList 需要下载的No1PPTList
     * @param BASE_PATH  文件保存的基础文件夹
     */
    private void downloadNo1PPT(List<No1PPT> no1PPTList, String BASE_PATH) {
        logger.info("------->  start!" +
                "   no1PPTList = " + no1PPTList +
                "   BASE_PATH = " + BASE_PATH);
        try {
            for (No1PPT no1PPT : no1PPTList) {
                int pptName = no1PPT.getId();
                String downloadUrl = no1PPT.getDownloadUrl().trim();
                String downloadPageUrl = no1PPT.getDownloadPageUrl();
                String fileExt = downloadUrl.substring(downloadUrl.length() - 4);
                HttpURLConnection conn = (HttpURLConnection) new URL(downloadUrl).openConnection();
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
                conn.setRequestProperty("referer", downloadPageUrl);
                InputStream inputStream = conn.getInputStream();
                // 创建输出流
                OutputStream outputStream = new FileOutputStream(BASE_PATH + pptName + fileExt);
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
            }
            logger.info("------->  end ! SUCCESS");
        } catch (Exception e) {
            logger.error("------->  ERROR!");
            logger.error(e.getMessage());
        }
    }
}
