package com.parse.ppt.poi.controller.download;

import com.parse.ppt.poi.service.common.download.FileDownloadService;
import net.sf.json.JSONArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Jupiter
 * @date 2018/03/06/15:10
 */
@Controller
@RequestMapping("/download")
public class FileDownloadController {
    private Logger logger = LogManager.getLogger(this.getClass());

    private final FileDownloadService fileDownloadService;

    @Autowired
    public FileDownloadController(FileDownloadService fileDownloadService) {
        this.fileDownloadService = fileDownloadService;
    }

    @RequestMapping("/downloadNo1PPT")
    @ResponseBody
    public String downloadNo1PPT(@RequestParam("id") String pptId,
                                 HttpSession session,
                                 HttpServletResponse response) {
        logger.info("------->  start! ");
        String result = fileDownloadService.downloadNo1PPT(Integer.valueOf(pptId), session, response);
        logger.info("------->  end!  result = " + result);
        return result;
    }
}
