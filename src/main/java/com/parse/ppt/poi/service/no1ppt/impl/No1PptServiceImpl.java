package com.parse.ppt.poi.service.no1ppt.impl;

import com.parse.ppt.poi.common.PathUtil;
import com.parse.ppt.poi.common.PptTag;
import com.parse.ppt.poi.common.ReturnCode;
import com.parse.ppt.poi.dao.persistence.No1PptDao;
import com.parse.ppt.poi.entity.No1PPT;
import com.parse.ppt.poi.service.common.download.PptDownloadService;
import com.parse.ppt.poi.service.common.ppt2img.Ppt2ImgService;
import com.parse.ppt.poi.service.no1ppt.No1PptService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * @author Jupiter
 */
@Service
public class No1PptServiceImpl implements No1PptService {
    private Logger logger = LogManager.getLogger(this.getClass());

    private final No1PptDao no1PptDao;
    private final Ppt2ImgService ppt2ImgService;
    private final PptDownloadService pptDownloadService;

    @Autowired
    public No1PptServiceImpl(No1PptDao no1PptDao, PptDownloadService pptDownloadService, Ppt2ImgService ppt2ImgService) {
        this.no1PptDao = no1PptDao;
        this.pptDownloadService = pptDownloadService;
        this.ppt2ImgService = ppt2ImgService;
    }

    @Override
    public No1PPT getNo1PptById(String no1PptId) {
        try {
            logger.info("------->  start!  pptId = " + no1PptId);
            No1PPT no1PPT = no1PptDao.getNo1PPTById(Integer.parseInt(no1PptId));
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
                File pptFile = PathUtil.getNo1PptFile(pptId);
                if (pptFile == null) {
                    logger.error("------->  ERROR!  本地仓库目录【" + PathUtil.getAbsoluteNo1PptPath(pptId) + "】路径下不存在PPT/PPTX文件！   return null");
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
    public List<No1PPT> getNo1PPTByKeyWordFuzzy(String keyword) {
        try {
            logger.info("------->  start!  keyword = " + keyword);
            List<No1PPT> resultList = no1PptDao.getNo1PPTByKeyWordFuzzy(keyword);
            logger.info("------->  end !    resultList = " + resultList);
            return resultList;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 null");
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<No1PPT> getNo1PPTByKeyWordsFuzzy(List<String> keywordsList) {
        try {
            logger.info("------->  start!   keywordsList = " + keywordsList);
            List<No1PPT> resultList = no1PptDao.getNo1PPTByKeyWordsFuzzy(keywordsList);
            logger.info("------->  end !    resultList = " + resultList);
            return resultList;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 null");
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Set<No1PPT> getNo1PPTByKeyWordsRelevancy(List<String> keywordsList) {
        try {
            logger.info("------->  start!   keywordsList = " + keywordsList);
            Set<No1PPT> resultSet = new LinkedHashSet<>();
            List<List<String>> keywordCombinationList = new ArrayList<>();
            for (int i = 1; i <= keywordsList.size(); i++) {
                combination(keywordsList, new ArrayList<>(), i, keywordCombinationList);
            }
            logger.info("keywordCombinationList = " + keywordCombinationList);
            // 反转 keywordCombinationList ，使关联度越高的 关键词组List 越靠前
            Collections.reverse(keywordCombinationList);
            logger.info("keywordCombinationList + " + keywordCombinationList);
            for (List<String> stringList : keywordCombinationList) {

                logger.info(stringList);
                List<No1PPT> resultList = no1PptDao.getNo1PPTByKeyWordsExact(stringList);
                logger.info(resultList);
                logger.info("+++++++++++++++++++++++++++++++++++++");
                resultSet.addAll(resultList);
            }
            logger.info("------->  end !    resultList = " + resultSet);
            return resultSet;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 null");
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<No1PPT> getNo1PPTByKeyWordsExact(List<String> keywordsList) {
        try {
            logger.info("------->  start!   keywordsList = " + keywordsList);
            List<No1PPT> resultList = no1PptDao.getNo1PPTByKeyWordsExact(keywordsList);
            logger.info("------->  end !    resultList = " + resultList);
            return resultList;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 null");
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
    public String updateNo1PPTFileName(String no1PptId, String pptFileName) {
        try {
            logger.info("------->  start! " +
                    "  pptId = " + no1PptId +
                    "  pptFileName = " + pptFileName);
            no1PptDao.updateNo1PPTFileName(Integer.parseInt(no1PptId), pptFileName);
            logger.info("------->  end ! SUCCESS");
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 FAILED ");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    public String downloadNo1PPT(String no1PptID, HttpServletRequest request, HttpServletResponse response) {
        logger.info("------->  start!" +
                "   pptId = " + no1PptID);
        try {
            String result = pptDownloadService.pptDownloader(no1PptID, request, response);
            logger.info("------->  end !   result = " + result);
            return result;
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
            String result = ppt2ImgService.ppt2imgs(no1PptID, PptTag.TYPE_NO1);
            logger.info("------->  end!" +
                    "   result = " + result);
            return result;
        } catch (Exception e) {
            logger.error("------->  ERROR! result = " + ReturnCode.FAILED);
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public int getImgsNum(String no1PptId) {
        try {
            logger.info("------->  start!" +
                    "   pptId = " + no1PptId);
            int result = ppt2ImgService.getImgsNum(no1PptId, PptTag.TYPE_NO1);
            logger.info("------->  end ! result = " + result);
            return result;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 -1 ");
            logger.error(e.getMessage());
        }
        return -1;
    }

    /**
     * 私有的 排列组合之组合 求值方法，从字符串List - stringList中取出 n 个 字符串进行组合
     *
     * @param stringList 字符串List
     * @param workSpace  工作空间List，直接传入一个 new ArrayList<>() 即可
     * @param k          选取个数
     * @param resultList 保存了结果的List
     */
    private void combination(List<String> stringList, List<String> workSpace, int k, List<List<String>> resultList) {
        List<String> copyData;
        List<String> copyWorkSpace;
        if (workSpace.size() == k) {
            List<String> tempList = new ArrayList<>(workSpace);
            resultList.add(tempList);
        }
        for (int i = 0; i < stringList.size(); i++) {
            copyData = new ArrayList<>(stringList);
            copyWorkSpace = new ArrayList<>(workSpace);
            copyWorkSpace.add(copyData.get(i));
            for (int j = i; j >= 0; j--)
                copyData.remove(j);
            combination(copyData, copyWorkSpace, k, resultList);
        }
    }

}
