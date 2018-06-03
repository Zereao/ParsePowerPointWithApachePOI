package com.parse.ppt.poi.service;

import com.parse.ppt.poi.common.PathUtil;
import com.parse.ppt.poi.common.ReturnCode;
import com.parse.ppt.poi.dao.No1PptDao;
import com.parse.ppt.poi.entity.No1PPT;
import com.parse.ppt.poi.service.common.PptDownloadService;
import com.parse.ppt.poi.service.common.PoiService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * @author Jupiter
 * @version 2018/05/29 14:48
 */
@Service
public class No1PptServiceImpl implements No1PptService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final No1PptDao no1PptDao;
    private final PoiService poiService;
    private final PptDownloadService pptDownloadService;

    @Autowired
    public No1PptServiceImpl(No1PptDao no1PptDao, PptDownloadService pptDownloadService, PoiService poiService) {
        this.no1PptDao = no1PptDao;
        this.pptDownloadService = pptDownloadService;
        this.poiService = poiService;
    }


    @Override
    public JSONArray getNo1PPT(int pageIndex, int pageSize) {
        logger.info("------->  start!    pageIndex = {}   pageSize = {}", pageIndex, pageSize);
        try {
            JSONArray no1pptJsonArray = new JSONArray();
            List<No1PPT> pptList = no1PptDao.getNo1PPT(pageIndex, pageSize);
            for (No1PPT ppt : pptList) {
                JSONObject json = new JSONObject();
                String no1pptId = String.valueOf(ppt.getId());
                json.put("id", no1pptId);
                json.put("description", ppt.getDescription());
                json.put("imgUrl", ppt.getImgUrl());
                // 根据no1pptId获取到本地仓库  ZeroFilesOutput 目录下对应的PPT文件
                File pptFile = PathUtil.getNo1PptFile(no1pptId);
                if (pptFile == null) {
                    logger.error("------->  ERROR!  本地仓库目录【{}】路径下不存在PPT/PPTX文件！", PathUtil.getNo1PptPath(no1pptId));
                    json.put("pptName", "");
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

    public String downloadNo1PPT(String no1PptID, HttpServletRequest request, HttpServletResponse response) {
        logger.info("------->  start!   pptId = {}", no1PptID);
        try {
            String result = pptDownloadService.pptDownloader(no1PptID, request, response);
            logger.info("------->  end !   result = {}", result);
            return result;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 FAILED ");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    //    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    public String ppt2img(String no1PptID) {
        logger.info("------->  start!   No1PptID = {}", no1PptID);
//        try {
//            String result = poiService.ppt2imgs(no1PptID, PptTag.TYPE_NO1);
//            logger.info("------->  end!" +
//                    "   result = " + result);
//            return result;
//        } catch (Exception e) {
//            logger.error("------->  ERROR! result = " + ReturnCode.FAILED);
//            logger.error(e.getMessage());
//        }
        return ReturnCode.FAILED;
    }

    @Override
    public int getImgsNum(String no1PptId) {
//        try {
//            logger.info("------->  start!" +
//                    "   pptId = " + no1PptId);
//            int result = poiService.getImgsNum(no1PptId, PptTag.TYPE_NO1);
//            logger.info("------->  end ! result = " + result);
//            return result;
//        } catch (Exception e) {
//            logger.error("------->  ERROR!  返回 -1 ");
//            logger.error(e.getMessage());
//        }
        return -1;
    }

}
