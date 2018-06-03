package com.parse.ppt.poi.controller;

import com.parse.ppt.poi.common.ReturnCode;
import com.parse.ppt.poi.service.No1PptService;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * No1PPT管理Controller
 *
 * @author Jupiter
 * @version 2018/03/09 10:41
 */
@Controller
@RequestMapping("/no1ppt")
public class No1PptController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final No1PptService no1PptService;

    @Autowired
    public No1PptController(No1PptService no1PptService) {
        this.no1PptService = no1PptService;
    }

    @RequestMapping("/loadNo1PPT")
    @ResponseBody
    public JSONArray loadNo1PPT(@RequestParam Integer pageIndex) {
        if (logger.isDebugEnabled()) {
            logger.info("------->  start!   pageIndex = {}", pageIndex);
        }
        JSONArray resultJsonArray = no1PptService.getNo1PptWithSize40(pageIndex);
        if (logger.isDebugEnabled()) {
            logger.info("------->  end!    resultJsonArray = {}", resultJsonArray.toString(2));
        }
        return resultJsonArray;
    }

    @RequestMapping("/downloadNo1PPT")
    @ResponseBody
    public String downloadNo1PPT(@RequestParam("id") String pptId,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        if (logger.isDebugEnabled()) {
            logger.info("------->  start!   pptId = {}", pptId);
        }
        String result = no1PptService.downloadNo1PPT(pptId, request, response);
        if (logger.isDebugEnabled()) {
            logger.info("------->  end!  result = {}", result);
        }
        return result;
    }

    @RequestMapping("/ppt2img")
    @ResponseBody
    public int no1ppt2img(@RequestParam("pptId") String pptId) {
        logger.info("------->  start!   pptId = {}", pptId);
        String result = no1PptService.ppt2img(pptId);
        int imgNum = 0;
        if (result.equals(ReturnCode.SUCCESS)) {
            imgNum = no1PptService.getImgsNum(pptId);
        }
        logger.info("------->  end!   imgNum = {}", imgNum);
        return imgNum;
    }
}
