package com.parse.ppt.poi.controller.poippt;

import com.parse.ppt.poi.service.poippt.PoiPptService;
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
    public String loadNo1PPT(@RequestParam String keywords,
                             HttpSession session) {
        logger.info("------->  start!" +
                "   keywords = " + keywords);
        poiPptService.getPoiPptByKeyword(keywords, session);
        logger.info("------->  end! ");
        return null;
    }
}
