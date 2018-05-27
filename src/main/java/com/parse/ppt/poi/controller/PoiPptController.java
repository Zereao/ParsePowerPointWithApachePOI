package com.parse.ppt.poi.controller;

import com.parse.ppt.poi.service.PoiPptService;
import net.sf.json.JSONArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/poi")
public class PoiPptController {
    private Logger logger = LogManager.getLogger(this.getClass());

    private final PoiPptService poiPptService;


    @Autowired
    public PoiPptController(PoiPptService poiPptService) {
        this.poiPptService = poiPptService;
    }

    @RequestMapping("/search")
    @ResponseBody
    public JSONArray getSearchResult(@RequestParam String keywords,
                                     HttpSession session) {
        logger.info("------->  start!" +
                "   keywords = " + keywords);
        JSONArray jsonArray = poiPptService.getSearchResult(keywords, session);
        logger.info("------->  end!" +
                "   jsonArray = " + jsonArray);
        return jsonArray;
    }

    @RequestMapping("/operatePoiPPT")
    @ResponseBody
    public int operatePoiPPT(@RequestParam("pptId") String no1pptId) {
        logger.info("------->  start!" +
                "   pptId = " + no1pptId);
        int imgNum = poiPptService.OperatePPT(no1pptId);
        logger.info("------->  end!" +
                "   imgNum = " + imgNum);
        return imgNum;
    }
}
