package com.parse.ppt.poi.controller.download;

import com.parse.ppt.poi.commom.ReturnCode;
import com.parse.ppt.poi.entity.No1PPT;
import com.parse.ppt.poi.service.common.no1ppt.No1PptService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    public String loadNo1PPT(HttpSession session) {
        logger.info("ExistDownloadController.loadNo1PPT   ------->  start! ");
        List<No1PPT> pptList = no1PptService.getNo1PPT(0, 40);
        logger.info("ExistDownloadController.loadNo1PPT   ------->  end! ");
        session.setAttribute("pptList", pptList);
        return ReturnCode.SUCCESS;
    }
}
