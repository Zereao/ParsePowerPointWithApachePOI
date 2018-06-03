package com.parse.ppt.poi.controller;

import com.parse.ppt.poi.service.PoiPptService;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * PoiPPT管理Controller
 *
 * @author Jupiter
 * @version 2018/03/10 12:41
 */
@Controller
@RequestMapping("/poi")
public class PoiPptController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PoiPptService poiPptService;

    @Autowired
    public PoiPptController(PoiPptService poiPptService) {
        this.poiPptService = poiPptService;
    }

    @RequestMapping("/search")
    @ResponseBody
    public JSONArray getSearchResult(@RequestParam String keywords,
                                     HttpSession session) {
        if (logger.isDebugEnabled()) {
            logger.info("------->  start!    keywords = {}", keywords);
        }
        JSONArray jsonArray = poiPptService.getSearchResult(keywords, session);
        if (logger.isDebugEnabled()) {
            logger.info("------->  end!   jsonArray = {}", jsonArray.toString(2));
        }
        return jsonArray;
    }

    @RequestMapping("/operatePoiPPT")
    @ResponseBody
    public int operatePoiPPT(@RequestParam("pptId") String no1pptId) {
        if (logger.isDebugEnabled()) {
            logger.info("------->  start!   pptId = {}", no1pptId);
        }
        int imgNum = poiPptService.OperatePPT(no1pptId);
        if (logger.isDebugEnabled()) {
            logger.info("------->  end!   imgNum = {}", imgNum);
        }
        return imgNum;
    }
}
