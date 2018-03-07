package com.parse.ppt.poi.service.common.no1ppt.impl;

import com.parse.ppt.poi.commom.ReturnCode;
import com.parse.ppt.poi.dao.persistence.No1PptDao;
import com.parse.ppt.poi.entity.No1PPT;
import com.parse.ppt.poi.service.common.no1ppt.No1PptService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class No1PptServiceImpl implements No1PptService {
    private Logger logger = LogManager.getLogger(this.getClass());
    private final No1PptDao no1PptDao;

    @Autowired
    public No1PptServiceImpl(No1PptDao no1PptDao) {
        this.no1PptDao = no1PptDao;
    }

    @Override
    public JSONArray getNo1PPT(int pageIndex, int pageSize) {
        logger.info("No1PptServiceImpl.getNo1PPT()   ------->  start!" +
                "  pageIndex = " + pageIndex +
                "  pageSize = " + pageSize);
        try {
            JSONArray jsonArray = new JSONArray();
            List<No1PPT> pptList = no1PptDao.getNo1PPT(pageIndex, pageSize);
            for (No1PPT ppt : pptList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("description", ppt.getSrcDescription());
                jsonObject.put("imgUrl", ppt.getSrcImgUrl());
                jsonObject.put("downloadUrl", ppt.getDownloadUrl());
                jsonArray.add(jsonObject);
            }
            logger.info("No1PptServiceImpl.getNo1PptByDescription()   ------->  end !");
            return jsonArray;
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
}
