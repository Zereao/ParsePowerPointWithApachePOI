package com.parse.ppt.poi.service.common.ppt2img.impl;

import com.parse.ppt.poi.common.ReturnCode;
import com.parse.ppt.poi.dao.persistence.No1PptDao;
import com.parse.ppt.poi.service.no1ppt.No1PptService;
import com.parse.ppt.poi.service.poi.hslf.PptOperateService;
import com.parse.ppt.poi.service.poi.xslf.PptxOperateService;
import com.parse.ppt.poi.service.common.ppt2img.No1Ppt2imgService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

@Service
public class No1Ppt2ImgServiceImpl implements No1Ppt2imgService {
    private Logger logger = LogManager.getLogger(this.getClass());

    private final PptOperateService pptOperateService;
    private final PptxOperateService pptxOperateService;
    private final No1PptDao no1PptDao;

    @Autowired
    public No1Ppt2ImgServiceImpl(PptOperateService pptOperateService, PptxOperateService pptxOperateService, No1PptDao no1PptDao) {
        this.pptOperateService = pptOperateService;
        this.pptxOperateService = pptxOperateService;
        this.no1PptDao = no1PptDao;
    }


    @Override
    public String ppt2img(String no1PptID) {
        logger.info("------->  start!" +
                "  No1PptID = " + no1PptID);
        try {
            String result = null;
            final String FILE_BASE_PATH = "文件输出/NO1PPTS/" + no1PptID + "/";
            // 存放转换后图片的文件夹
            String ppt2imgPath = FILE_BASE_PATH + "PPT2IMG/";
            File ppt2imgFolder = new File(ppt2imgPath);
            boolean isFolderAndExists = ppt2imgFolder.isDirectory() && ppt2imgFolder.exists();
            boolean isNotNull = ppt2imgFolder.listFiles() != null && Objects.requireNonNull(ppt2imgFolder.listFiles()).length > 0;
            if (isFolderAndExists && isNotNull) {
                logger.info("------->  end !  ID = " + no1PptID + " 的PPT已经被转化成了图片，可以直接读取。   result = " + ReturnCode.SUCCESS);
                return ReturnCode.SUCCESS;
            }
            File pptPath = new File(FILE_BASE_PATH);
            // 返回PPT所在目录下的所有文件
            File[] files = pptPath.listFiles();
            // 已经提前处理好，使得每个目录下都只有一个PPT/PPTX文件
            assert files != null;
            // 数组index为0时即返回的是我们需要的ppt/pptx文件对象
            String fileName = files[0].getName();
            no1PptDao.updateNo1PPTFileName(Integer.parseInt(no1PptID), fileName);
            logger.info("++++++++++++++++");
            if (fileName.toLowerCase().contains(".ppt") && (!(fileName.toLowerCase().contains(".pptx")))) {
                result = pptOperateService.ppt2img(files[0]);
            } else if (fileName.toLowerCase().contains(".pptx")) {
                result = pptxOperateService.pptx2img(files[0]);
            }
            logger.info("------->  end ! result = " + result);
            return result;
        } catch (Exception e) {
            logger.error("------->  ERROR! result = " + ReturnCode.FAILED);
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }
}
