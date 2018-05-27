package com.parse.ppt.poi.controller;

import com.parse.ppt.poi.service.AdministratorService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author Jupiter
 * @version 2018/03/09 11:52
 */
@Controller
@RequestMapping("/admin")
public class AdministratorController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AdministratorService administratorService;

    @Autowired
    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @RequestMapping("/setEssay")
    @ResponseBody
    public JSONObject setEssay(@RequestParam("essayTitle") String essayTitle,
                               @RequestParam("essayContent") String essayContent,
                               HttpSession session) {
        if (logger.isDebugEnabled()) {
            logger.info("------->  start!   essayTitle = {}    essayContent = {} ", essayTitle, essayContent);
        }
        JSONObject resultJson = administratorService.setMainPageEssay(essayTitle, essayContent, session);
        if (logger.isDebugEnabled()) {
            logger.info("------->  end!  resultJson = {}", resultJson);
        }
        return resultJson;
    }
}
