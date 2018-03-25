package com.parse.ppt.poi.service.common.ppt2img.impl;

import com.parse.ppt.poi.common.PathUtil;
import com.parse.ppt.poi.common.ReturnCode;
import com.parse.ppt.poi.service.common.poi.hslf.PptOperateService;
import com.parse.ppt.poi.service.common.poi.xslf.PptxOperateService;
import com.parse.ppt.poi.service.common.ppt2img.Ppt2ImgService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

@Service
public class Ppt2ImgServiceImpl implements Ppt2ImgService {
    private Logger logger = LogManager.getLogger(this.getClass());

    private final PptOperateService pptOperateService;
    private final PptxOperateService pptxOperateService;

    @Autowired
    public Ppt2ImgServiceImpl(PptOperateService pptOperateService, PptxOperateService pptxOperateService) {
        this.pptOperateService = pptOperateService;
        this.pptxOperateService = pptxOperateService;
    }

    @Override
    public String ppt2imgs(String pptId, String pptTag) {
        logger.info("------->  start!" +
                "   pptId = " + pptId +
                "   pptTag = " + pptTag);
        try {
            String result = null;
            // 存放转换后图片的文件夹
            String ppt2imgsPath = PathUtil.getAbsolutePpt2ImgPathByTag(pptId, pptTag);
            File ppt2imgFolder = new File(ppt2imgsPath);
            boolean isFolderAndExists = ppt2imgFolder.isDirectory() && ppt2imgFolder.exists();
            boolean isNotNull = ppt2imgFolder.listFiles() != null && Objects.requireNonNull(ppt2imgFolder.listFiles()).length > 0;
            if (isFolderAndExists && isNotNull) {
                logger.info("------->  end !" +
                        "  ID = " + pptId + " 的PPT已经被转化成了图片，" +
                        "存在于 Path = 【" + ppt2imgsPath + "】下，可以直接读取。" +
                        "   result = " + ReturnCode.SUCCESS);
                return ReturnCode.SUCCESS;
            }
            File pptFile = PathUtil.getPptFileByTag(pptId, pptTag);
            // 返回PPT所在目录下的所有文件
            if (pptFile == null) {
                logger.warn("------->  end !  PATH = 【" + PathUtil.getAbsolutePptPathByTag(pptId, pptTag) + "】" +
                        "路径下不存在PPTX文件！" + "   return " + ReturnCode.RESOURCES_NOT_EXISTS);
                return ReturnCode.RESOURCES_NOT_EXISTS;
            }
            String fileName = pptFile.getName();
            if (fileName.toLowerCase().contains(".ppt") && (!(fileName.toLowerCase().contains(".pptx")))) {
                result = pptOperateService.ppt2img(pptId, pptFile);
            } else if (fileName.toLowerCase().contains(".pptx")) {
                result = pptxOperateService.pptx2img(pptId, pptFile);
            }
            logger.info("------->  end ! result = " + result);
            return result;
        } catch (Exception e) {
            logger.error("------->  ERROR! result = " + ReturnCode.FAILED);
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public int getImgsNum(String pptId, String pptTag) {
        logger.info("------->  start!" +
                "   pptID = " + pptId +
                "   pptTag = " + pptTag);
        try {
            // 存放转换后图片的文件夹
            String ppt2imgPath = PathUtil.getAbsolutePpt2ImgPathByTag(pptId, pptTag);
            if (ppt2imgPath == null || "".equals(ppt2imgPath)) {
                logger.info("------->  ERROR!   pptTag错误！ pptTag = " + pptTag);
                return -2;
            }
            File ppt2imgFolder = new File(ppt2imgPath);
            File[] files = ppt2imgFolder.listFiles();
            int imgsNum = 0;
            if (files != null) {
                imgsNum = files.length;
            }
            logger.info("------->  end!" +
                    "   imgsNum = " + imgsNum);
            return imgsNum;
        } catch (Exception e) {
            logger.error("------->  ERROR!  return -1 ");
            logger.error(e.getMessage());
        }
        return -1;
    }
}
