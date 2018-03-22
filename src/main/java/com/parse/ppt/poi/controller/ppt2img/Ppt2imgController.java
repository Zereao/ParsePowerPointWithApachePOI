package com.parse.ppt.poi.controller.ppt2img;

import com.parse.ppt.poi.service.common.ppt2img.No1Ppt2imgService;
import com.parse.ppt.poi.service.no1ppt.No1PptService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ppt2img")
public class Ppt2imgController {
    private Logger logger = LogManager.getLogger(this.getClass());

    private final No1PptService no1PptService;

    @Autowired
    public Ppt2imgController(No1PptService no1PptService) {
        this.no1PptService = no1PptService;
    }

    @RequestMapping("/no1ppt2img")
    @ResponseBody
    public int no1ppt2img(@RequestParam("pptId") String pptId) {
        logger.info("------->  start! " +
                "  pptId = " + pptId);
        int imgNum = no1PptService.getImgsNum(pptId);
        logger.info("------->  end! " +
                "  imgNum = " + imgNum);
        return imgNum;
    }
}
