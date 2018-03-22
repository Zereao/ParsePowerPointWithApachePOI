package com.parse.ppt.poi.service.poi.xslf.impl;

import com.parse.ppt.poi.common.PathUtil;
import com.parse.ppt.poi.common.ReturnCode;
import com.parse.ppt.poi.service.poi.xslf.PptxOperateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xslf.usermodel.*;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

@Service
public class PptxOperateServiceImpl implements PptxOperateService {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public String pptx2img(String pptxId, File pptxFile) {
        logger.info("------->  start!   pptPath = " + pptxFile.getPath());
        try (
                FileInputStream inputStream = new FileInputStream(pptxFile);
                XMLSlideShow pptx = new XMLSlideShow(inputStream)
        ) {
            // pptx转换图片后的图片所在的父目录
            final String PPT2IMG_PATH = PathUtil.getAbstractPpt2imgPath(pptxId);
            File mkDir = new File(PPT2IMG_PATH);
            if (!mkDir.exists()) {
                boolean isMkDir = mkDir.mkdirs();
            }
            Dimension pageSize = pptx.getPageSize();
            for (int i = 0; i < pptx.getSlides().size(); i++) {
                //防止中文乱码-设置每一张字体族 都为 宋体      ++++++++++++++++++++++++++ 备用代码
                /*
                for (XSLFShape shape : pptx.getSlides().get(i).getShapes()) {
                    if (shape instanceof XSLFTextShape) {
                        XSLFTextShape tsh = (XSLFTextShape) shape;
                        for (XSLFTextParagraph p : tsh) {
                            for (XSLFTextRun r : p) {
                                r.setFontFamily("宋体");
                            }
                        }
                    }
                }
                */
                BufferedImage img = new BufferedImage(pageSize.width, pageSize.height, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = img.createGraphics();
                // clear the drawing area
                graphics.setPaint(Color.white);
                graphics.fill(new Rectangle2D.Float(0, 0, pageSize.width, pageSize.height));
                // render - 给予，提交，表达   这里就是提交信息，开始绘画图片
                pptx.getSlides().get(i).draw(graphics);
                // save the output
                String filename = PPT2IMG_PATH + (i + 1) + ".png";
                FileOutputStream out = new FileOutputStream(filename);
                javax.imageio.ImageIO.write(img, "png", out);
                out.close();
            }
            logger.info("------->  end! result = " + ReturnCode.SUCCESS);
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 FAILED ");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }
}
