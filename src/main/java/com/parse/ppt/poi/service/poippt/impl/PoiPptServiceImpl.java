package com.parse.ppt.poi.service.poippt.impl;

import com.parse.ppt.poi.common.PathUtil;
import com.parse.ppt.poi.entity.No1PPT;
import com.parse.ppt.poi.entity.PoiPPT;
import com.parse.ppt.poi.service.common.poi.service.PoiService;
import com.parse.ppt.poi.service.no1ppt.No1PptService;
import com.parse.ppt.poi.service.poippt.PoiPptService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@Service
public class PoiPptServiceImpl implements PoiPptService {
    private Logger logger = LogManager.getLogger(this.getClass());

    private final PoiService poiService;
    private final No1PptService no1PptService;

    @Autowired
    public PoiPptServiceImpl(PoiService poiService, No1PptService no1PptService) {
        this.poiService = poiService;
        this.no1PptService = no1PptService;
    }

    @Override
    public List<String> spiltKeywords(String keywords) {
        logger.info("------->  start!" +
                "   keywords = " + keywords);
        try {
            // 按照 半角/全角逗号拆分
            String[] strArray_1 = keywords.split(",");
            String[] strArray_2 = keywords.split("，");
            List<String> keywordList = new ArrayList<>();
            keywordList.addAll(Arrays.asList(strArray_1));
            keywordList.addAll(Arrays.asList(strArray_2));
            logger.info("------->  end ! keywordList = " + keywordList);
            return keywordList;
        } catch (Exception e) {
            logger.error("------->  ERROR!  return null");
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public PoiPPT getPoiPptByKeyword(String keywords, HttpSession session) {
        logger.info("------->  start!" +
                "   keywords = " + keywords);
        try {
            List<String> keywordList = spiltKeywords(keywords);
            // 根据关键词关联度搜索本地仓库中的No1PPT，存于LinkedHashSet中，保证有序、去重
            Set<No1PPT> no1PPTSet = no1PptService.getNo1PPTByKeyWordsRelevancy(keywordList);
            // 为了用户体验，截取其前16条信息先进行筛选
            List<No1PPT> sessionList = new ArrayList<>();
            int index = 0;
            for (No1PPT no1PPT : no1PPTSet) {
                sessionList.add(no1PPT);
                index++;
                if (index == 16) {
                    break;
                }
            }
            // 把这次已经读取了的List 以及 index 放进Session
            session.setAttribute("sessionList", sessionList);
            session.setAttribute("index", 16);
            // 挑选符合条件的No1PPT————幻灯片张数大于 7 的，同时通过OCR获取到图片的 广告页面信息
            List<Map<No1PPT, int[]>> resultNo1PPTList = poiService.selectPPTSync(sessionList, 7);
            // 如果 生成目录 存在，则删除
            String targetPath = PathUtil.getAbsolutePoiRebuildPptPath();
            File deleteFile = new File(targetPath);
            if (deleteFile.exists() && deleteFile.isDirectory()) {
                PathUtil.deleteDir(deleteFile);
            }
            String result = poiService.rebuildPPTSync(resultNo1PPTList);
            logger.info(result);
        } catch (Exception e) {
            logger.error("------->  ERROR!  return null");
            logger.error(e.getMessage());
        }

        logger.info("------->  end! ");
        return null;
    }
}
