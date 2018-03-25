package com.parse.ppt.poi.controller.poi;

import com.parse.ppt.poi.service.poi.PoiPptService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/poi")
public class PoiPptController {
    private Logger logger = LogManager.getLogger(this.getClass());

    private final PoiPptService poiPptService;

    @Autowired
    public PoiPptController(PoiPptService poiPptService) {
        this.poiPptService = poiPptService;
    }

    @RequestMapping("/rebulidNo1ppt")
    @ResponseBody
    public String loadNo1PPT(@RequestParam Integer pageIndex) {
        logger.info("------->  start! ");
        logger.info("------->  end! ");
        return null;
    }
}
