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
import java.io.*;
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
                String pptId = String.valueOf(ppt.getId());
                json.put("id", pptId);
                json.put("description", ppt.getSrcDescription());
                json.put("imgUrl", ppt.getSrcImgUrl());
                // 根据pptId获取到本地仓库  ZeroFilesOutput 目录下对应的PPT文件
                File pptFile = PathUtil.getPptFile(pptId);
                if (pptFile == null) {
                    logger.error("------->  ERROR!  本地仓库目录【" + PathUtil.getAbsolutePptPath(pptId) + "】路径下不存在PPT/PPTX文件！   return null");
                } else {
                    json.put("pptName", pptFile.getName());
                }
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
    public String downloadNo1PPT(String pptId, HttpServletRequest request, HttpServletResponse response) {
        logger.info("------->  start!" +
                "   pptId = " + pptId);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            File pptFile = PathUtil.getPptFile(pptId);
            if (pptFile == null) {
                logger.error("------->  ERROR!" +
                        "   本地不存在 pptId = " + pptId + " 的PPT！  return " + ReturnCode.RESOURCES_NOT_EXISTS);
                return ReturnCode.RESOURCES_NOT_EXISTS;
            }
            inputStream = new FileInputStream(pptFile);
            bufferedInputStream = new BufferedInputStream(inputStream);
            // 创建输出流
            outputStream = response.getOutputStream();
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            // 创建缓冲区
            byte[] buffer = new byte[1024];
            int len = 0;
            // 循环将输入流中的内容读取到缓冲区中
            while ((len = bufferedInputStream.read(buffer)) > 0) {
                // 输出缓冲区内容到浏览器，实现文件下载
                bufferedOutputStream.write(buffer, 0, len);
            }
            // 如果用户登录，则添加用户的下载记录到数据库
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                UserDownloadHistory userDownloadHistory = new UserDownloadHistory(user.getEmail(), Integer.parseInt(pptId));
                String result = userDownloadHistoryService.addDownloadHistory(userDownloadHistory);
            }
            logger.info("------->  end ! SUCCESS");
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 FAILED ");
            logger.error(e.getMessage());
        } finally {
            try {
                // 关闭输出流   关闭顺序，还是要深入理解下
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                // 关闭文件流
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String ppt2img(String no1PptID) {
        logger.info("------->  start!" +
                "  No1PptID = " + no1PptID);
        try {
            String result = null;
            // 存放转换后图片的文件夹
            final String PPT2IMG_PATH = PathUtil.getAbsolutePpt2imgPath(no1PptID);
            File ppt2imgFolder = new File(PPT2IMG_PATH);
            logger.info(ppt2imgFolder.getAbsolutePath());
            boolean isFolderAndExists = ppt2imgFolder.isDirectory() && ppt2imgFolder.exists();
            boolean isNotNull = ppt2imgFolder.listFiles() != null && Objects.requireNonNull(ppt2imgFolder.listFiles()).length > 0;
            if (isFolderAndExists && isNotNull) {
                logger.info("------->  end !  ID = " + no1PptID + " 的PPT已经被转化成了图片，可以直接读取。   result = " + ReturnCode.SUCCESS);
                return ReturnCode.SUCCESS;
            }
            File pptFile = PathUtil.getPptFile(no1PptID);
            // 返回PPT所在目录下的所有文件
            if (pptFile == null) {
                logger.warn("------->  end !  PATH = " + PathUtil.getAbsolutePptPath(no1PptID) + "  路径下不存在PPTX文件！" +
                        "   return " + ReturnCode.RESOURCES_NOT_EXISTS);
                return ReturnCode.RESOURCES_NOT_EXISTS;
            }
            String fileName = pptFile.getName();
            result = updateNo1PPTFileName(no1PptID, fileName);
            if (fileName.toLowerCase().contains(".ppt") && (!(fileName.toLowerCase().contains(".pptx")))) {
                result = pptOperateService.ppt2img(no1PptID, pptFile);
            } else if (fileName.toLowerCase().contains(".pptx")) {
                result = pptxOperateService.pptx2img(no1PptID, pptFile);
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
            // 存放转换后图片的文件夹
            String ppt2imgPath = PathUtil.getAbsolutePpt2imgPath(pptId);
            File ppt2imgFolder = new File(ppt2imgPath);
            File[] files = ppt2imgFolder.listFiles();
            int imgsNum = 0;
            if (files != null) {
                imgsNum = files.length;
            }
            logger.info("------->  end!" +
                    "   imgsNum = " + imgsNum);
            return imgsNum;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回  -1 ");
            logger.error(e.getMessage());
        }
        return -1;
    }


}
