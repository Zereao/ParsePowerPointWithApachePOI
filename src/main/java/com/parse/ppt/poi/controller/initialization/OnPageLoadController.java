package com.parse.ppt.poi.controller.initialization;

import com.parse.ppt.poi.service.common.initialization.OnPageLoadService;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jupiter
 * @date 2018/03/09/10:41
 */
@Controller
@RequestMapping("/onMainPageLoad")
public class OnPageLoadController {
    private Logger logger = LogManager.getLogger(this.getClass());

    private final OnPageLoadService onPageLoadService;

    @Autowired
    public OnPageLoadController(OnPageLoadService onPageLoadService) {
        this.onPageLoadService = onPageLoadService;
    }

    @RequestMapping("/getMainPageLoadInfo")
    @ResponseBody
    public JSONObject getMainPageLoadInfo(HttpServletRequest request) {
        logger.info("------->  start! ");
        JSONObject resultJson = onPageLoadService.getMainPageLoadInfo(request);
        logger.info("------->  end!");
        return resultJson;
    }

}
