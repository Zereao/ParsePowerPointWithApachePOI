package com.parse.ppt.poi.controller;

import com.parse.ppt.poi.service.InitializeService;
import net.sf.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jupiter
 * @version 2018/03/09 10:41
 */
@Controller
@RequestMapping("/initialize")
public class InitializeController {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    private final InitializeService initializeService;

    @Autowired
    public InitializeController(InitializeService initializeService) {
        this.initializeService = initializeService;
    }

    @RequestMapping("/getInitializeInfo")
    @ResponseBody
    public JSONObject getMainPageLoadInfo(HttpServletRequest request) {
        if (logger.isDebugEnabled()) {
            logger.info("------->  start! ");
        }
        JSONObject resultJson = initializeService.getInitializeInfo(request);
        if (logger.isDebugEnabled()) {
            logger.info("------->  end!");
        }
        return resultJson;
    }
}
