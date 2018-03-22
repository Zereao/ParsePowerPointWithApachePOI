package com.parse.ppt.poi.controller.no1ppt;

import com.parse.ppt.poi.service.no1ppt.No1PptService;
import net.sf.json.JSONArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/no1ppt")
public class No1PptController {
    private Logger logger = LogManager.getLogger(this.getClass());

    private final No1PptService no1PptService;

    @Autowired
    public No1PptController(No1PptService no1PptService) {
        this.no1PptService = no1PptService;
    }

    @RequestMapping("/loadNo1PPT")
    @ResponseBody
    public JSONArray loadNo1PPT(@RequestParam Integer pageIndex) {
        logger.info("------->  start! ");
        JSONArray resultJsonArray = no1PptService.getNo1PptWithSize40(pageIndex);
        logger.info("------->  end! ");
        return resultJsonArray;
    }

    @RequestMapping("/downloadNo1PPT")
    @ResponseBody
    public String downloadNo1PPT(@RequestParam("id") String pptId,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        logger.info("------->  start! ");
        String result = no1PptService.downloadNo1PPT(Integer.valueOf(pptId), request, response);
        logger.info("------->  end!  result = " + result);
        return result;
    }
}
