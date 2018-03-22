package com.parse.ppt.poi.service.no1ppt.impl;

import com.parse.ppt.poi.common.PathUtil;
import com.parse.ppt.poi.common.ReturnCode;
import com.parse.ppt.poi.dao.persistence.No1PptDao;
import com.parse.ppt.poi.entity.No1PPT;
import com.parse.ppt.poi.entity.User;
import com.parse.ppt.poi.entity.UserDownloadHistory;
import com.parse.ppt.poi.service.common.history.UserDownloadHistoryService;
import com.parse.ppt.poi.service.no1ppt.No1PptService;
import com.parse.ppt.poi.service.poi.hslf.PptOperateService;
import com.parse.ppt.poi.service.poi.xslf.PptxOperateService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * @author Jupiter
 */
@Service
public class No1PptServiceImpl implements No1PptService {
    private Logger logger = LogManager.getLogger(this.getClass());
    private final No1PptDao no1PptDao;
    private final PptOperateService pptOperateService;
    private final PptxOperateService pptxOperateService;

    private final UserDownloadHistoryService userDownloadHistoryService;

    @Autowired
    public No1PptServiceImpl(No1PptDao no1PptDao, UserDownloadHistoryService userDownloadHistoryService, PptOperateService pptOperateService, PptxOperateService pptxOperateService) {
        this.no1PptDao = no1PptDao;
        this.userDownloadHistoryService = userDownloadHistoryService;
        this.pptOperateService = pptOperateService;
        this.pptxOperateService = pptxOperateService;
    }

    @Override
    public No1PPT getNo1PptById(String pptId) {
        try {
            logger.info("------->  start!  pptId = " + pptId);
            No1PPT no1PPT = no1PptDao.getNo1PPTById(Integer.parseInt(pptId));
            logger.info("------->  end ! no1PPT = " + no1PPT);
            return no1PPT;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 null ");
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public JSONArray getNo1PPT(int pageIndex, int pageSize) {
        logger.info("------->  start!" +
                "  pageIndex = " + pageIndex +
                "  pageSize = " + pageSize);
        try {
            JSONArray no1pptJsonArray = new JSONArray();
            List<No1PPT> pptList = no1PptDao.getNo1PPT(pageIndex, pageSize);
            for (No1PPT ppt : pptList) {
                JSONObject json = new JSONObject();
                json.put("id", String.valueOf(ppt.getId()));
                json.put("description", ppt.getSrcDescription());
                json.put("imgUrl", ppt.getSrcImgUrl());
                json.put("downloadPageUrl", ppt.getDownloadPageUrl());
                json.put("downloadUrl", ppt.getDownloadUrl());
                json.put("pptFileName", ppt.getPptFileName());
                no1pptJsonArray.add(json);
            }
            logger.info("------->  end !");
            return no1pptJsonArray;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 null ");
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public JSONArray getNo1PptWithSize40(int pageIndex) {
        return getNo1PPT(pageIndex, 40);
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
    public String updateNo1PPTFileName(String pptId, String pptFileName) {
        try {
            logger.info("------->  start! " +
                    "  pptId = " + pptId +
                    "  pptFileName = " + pptFileName);
            no1PptDao.updateNo1PPTFileName(Integer.parseInt(pptId), pptFileName);
            logger.info("------->  end ! SUCCESS");
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 FAILED ");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String downloadNo1PPT(int pptId, HttpServletRequest request, HttpServletResponse response) {
        try {
            logger.info("------->  start!");
            No1PPT pptTemp = getNo1PptById(String.valueOf(pptId));
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

            // 如果用户登录，则添加用户的下载记录到数据库
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                UserDownloadHistory userDownloadHistory = new UserDownloadHistory(user.getEmail(), pptId);
                String result = userDownloadHistoryService.addDownloadHistory(userDownloadHistory);
            }

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

    @Override
    public String ppt2img(String no1PptID) {
        logger.info("------->  start!" +
                "  No1PptID = " + no1PptID);
        try {
            String result = null;
            final String PPT_PATH = PathUtil.getAbstractPptPath(no1PptID);
            // 存放转换后图片的文件夹
            final String PPT2IMG_PATH = PathUtil.getAbstractPpt2imgPath(no1PptID);
            File ppt2imgFolder = new File(PPT2IMG_PATH);
            boolean isFolderAndExists = ppt2imgFolder.isDirectory() && ppt2imgFolder.exists();
            boolean isNotNull = ppt2imgFolder.listFiles() != null && Objects.requireNonNull(ppt2imgFolder.listFiles()).length > 0;
            if (isFolderAndExists && isNotNull) {
                logger.info("------->  end !  ID = " + no1PptID + " 的PPT已经被转化成了图片，可以直接读取。   result = " + ReturnCode.SUCCESS);
                return ReturnCode.SUCCESS;
            }
            File pptPath = new File(PPT_PATH);
            // 返回PPT所在目录下的所有文件
            File[] files = pptPath.listFiles();
            if (files == null || files.length < 1) {
                logger.info("------->  end !  PATH = " + PPT_PATH + "  路径下不存在任何文件，为空！");
                return ReturnCode.FAILED;
            }
            for (File file : files) {
                String fileName = file.getName();
                updateNo1PPTFileName(no1PptID, fileName);
                if (fileName.toLowerCase().contains(".ppt") && (!(fileName.toLowerCase().contains(".pptx")))) {
                    result = pptOperateService.ppt2img(no1PptID, file);
                } else if (fileName.toLowerCase().contains(".pptx")) {
                    result = pptxOperateService.pptx2img(no1PptID, file);
                } else {
                    logger.info("------->  end !  PATH = " + PPT_PATH + "  路径下不存在PPTX文件！");
                    return ReturnCode.FAILED;
                }
            }
            logger.info("------->  end ! result = " + result);
            return result;
        } catch (Exception e) {
            logger.error("------->  ERROR! result = " + ReturnCode.FAILED);
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public int getImgsNum(String pptId) {
        try {
            logger.info("------->  start!" +
                    "   pptId = " + pptId);
            String result = ppt2img(pptId);
            if (result.equals(ReturnCode.SUCCESS)) {
                // 存放转换后图片的文件夹
                String ppt2imgPath = PathUtil.getAbstractPpt2imgPath(pptId);
                File ppt2imgFolder = new File(ppt2imgPath);
                int imgsNum = Objects.requireNonNull(ppt2imgFolder.listFiles()).length;
                logger.info("------->  end!" +
                        "   imgsNum = " + imgsNum);
                return imgsNum;
            }
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回  -1 ");
            logger.error(e.getMessage());
        }
        return -1;
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
