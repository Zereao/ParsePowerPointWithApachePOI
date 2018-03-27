package com.parse.ppt.poi.service.common.poi.operate.hslf.impl;

import com.parse.ppt.poi.common.ReturnCode;
import com.parse.ppt.poi.service.common.poi.operate.hslf.PptOperateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFSlideShowImpl;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

@Service
public class PptOperateServiceImpl implements PptOperateService {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public String ppt2img(File pptFile, String targetPath) {
        logger.info("------->  start!" +
                "   pptFile = " + pptFile.getAbsolutePath() +
                "   targetPath = " + targetPath);
        try (
                FileInputStream inputStream = new FileInputStream(pptFile);
                HSLFSlideShow ppt = new HSLFSlideShow(inputStream)
        ) {
            // 如果输出目录不存在，则创建
            File target = new File(targetPath);
            if (!target.exists()) {
                boolean isMkDirs = target.mkdirs();
            }
            Dimension pageSize = ppt.getPageSize();
            // 图片被命名为 1.png , 2.png , 3.png
            int index = 1;
            for (HSLFSlide slide : ppt.getSlides()) {
                BufferedImage img = new BufferedImage(pageSize.width, pageSize.height, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = img.createGraphics();
                // clear the drawing area
                graphics.setPaint(Color.white);
                graphics.fill(new Rectangle2D.Float(0, 0, pageSize.width, pageSize.height));
                // render
                slide.draw(graphics);
                // save the output
                String filename = targetPath + index + ".png";
                FileOutputStream out = new FileOutputStream(filename);
                javax.imageio.ImageIO.write(img, "png", out);
                out.close();
                index++;
            }
            logger.info("------->  end! result = " + ReturnCode.SUCCESS);
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 FAILED ");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public boolean isPageMatchCondition(File pptFile, int minPageNum) {
        logger.info("------->  start!" +
                "   pptFile = " + pptFile.getAbsolutePath() +
                "   minPageNum = " + minPageNum);
        try (
                InputStream pptFileInputStream = new FileInputStream(pptFile);
                HSLFSlideShow slideShow = new HSLFSlideShow(new HSLFSlideShowImpl(pptFileInputStream))
        ) {
            int slidesNum = slideShow.getSlides().size();
            boolean result = slidesNum >= minPageNum;
            logger.info("------->  end!  result = " + result);
            return result;
        } catch (Exception e) {
            logger.error("------->  ERROR!   返回 false");
            logger.error(e.getMessage());
        }
        return false;
    }
}
