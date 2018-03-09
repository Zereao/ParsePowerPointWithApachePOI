package com.parse.ppt.poi.controller.admin;

import com.parse.ppt.poi.service.admin.AdministratorService;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author Jupiter
 * @date 2018/03/09/11:52
 */
@Controller
@RequestMapping("/admin")
public class AdministratorController {
    private Logger logger = LogManager.getLogger(this.getClass());

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
        logger.info("AdministratorController.setEssay   ------->  start! " +
                "  essayTitle = " + essayTitle +
                "  essayContent = " + essayContent);
        JSONObject resultJson = administratorService.setMainPageEssay(essayTitle, essayContent, session);
        logger.info("AdministratorController.setEssay   ------->  end! " +
                " resultJson = " + resultJson);
        return resultJson;
    }
}
