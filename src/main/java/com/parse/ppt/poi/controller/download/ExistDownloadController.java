package com.parse.ppt.poi.controller.download;

import com.parse.ppt.poi.service.common.no1ppt.No1PptService;
import net.sf.json.JSONArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jupiter
 * @date 2018/03/06/15:10
 */
@Controller
@RequestMapping("/download")
public class ExistDownloadController {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private No1PptService no1PptService;

    @RequestMapping("/loadNo1PPT")
    @ResponseBody
    public JSONArray loadNo1PPT() {
        logger.info("ExistDownloadController.loadNo1PPT   ------->  start! ");
        JSONArray resultJsonArray = no1PptService.getNo1PPT(0, 40);
        logger.info("ExistDownloadController.loadNo1PPT   ------->  end! ");
        return resultJsonArray;
    }
}
