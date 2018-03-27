package com.parse.ppt.poi.service.common.poi.operate.xslf.impl;

import com.parse.ppt.poi.common.PathUtil;
import com.parse.ppt.poi.common.PptTag;
import com.parse.ppt.poi.common.ReturnCode;
import com.parse.ppt.poi.entity.No1PPT;
import com.parse.ppt.poi.service.common.poi.operate.xslf.PptxOperateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xslf.usermodel.*;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;

@Service
public class PptxOperateServiceImpl implements PptxOperateService {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public String pptx2img(File pptxFile, String targetPath) {
        logger.info("------->  start!" +
                "   pptPath = " + pptxFile.getPath());
        try (
                FileInputStream inputStream = new FileInputStream(pptxFile);
                XMLSlideShow pptx = new XMLSlideShow(inputStream)
        ) {
            // 如果输出目录不存在，则创建
            File target = new File(targetPath);
            if (!target.exists()) {
                boolean isMkDirs = target.mkdirs();
            }
            Dimension pageSize = pptx.getPageSize();
            for (int i = 0; i < pptx.getSlides().size(); i++) {
                /*防止中文乱码-设置每一张字体族 都为 宋体      ++++++++++++++++++++++++++ 备用代码
                for (XSLFShape shape : pptx.getSlides().get(i).getShapes()) {
                    if (shape instanceof XSLFTextShape) {
                        XSLFTextShape tsh = (XSLFTextShape) shape;
                        for (XSLFTextParagraph p : tsh) {
                            for (XSLFTextRun r : p) {
                                r.setFontFamily("宋体");
                            }
                        }
                    }
                }*/
                BufferedImage img = new BufferedImage(pageSize.width, pageSize.height, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = img.createGraphics();
                // clear the drawing area
                graphics.setPaint(Color.white);
                graphics.fill(new Rectangle2D.Float(0, 0, pageSize.width, pageSize.height));
                // render - 给予，提交，表达   这里就是提交信息，开始绘画图片
                pptx.getSlides().get(i).draw(graphics);
                // save the output
                String filename = targetPath + (i + 1) + ".png";
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

    @Override
    public boolean isPageMatchCondition(File pptxFile, int minPageNum) {
        logger.info("------->  start!" +
                "   pptFile = " + pptxFile.getAbsolutePath() +
                "   minPageNum = " + minPageNum);
        try (
                InputStream pptxFileInputStream = new FileInputStream(pptxFile);
                XMLSlideShow slideShow = new XMLSlideShow(pptxFileInputStream)
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

    @Override
    public String rebuildPPTX(No1PPT no1PPT, int[] adPageIndexs) {
        logger.info("------->  start!" +
                "   No1PPT no1PPT = " + no1PPT +
                "   adPageIndexs = " + Arrays.toString(adPageIndexs));
        OutputStream outputStream = null;
        try (
                InputStream pptFileInputStream = new FileInputStream(Objects.requireNonNull(PathUtil.getNo1PptFile(String.valueOf(no1PPT.getId()))));
                XMLSlideShow slideShow = new XMLSlideShow(pptFileInputStream);
        ) {
            for (int adPageIndex : adPageIndexs) {
                slideShow.removeSlide(adPageIndex);
            }
            String targetPath = PathUtil.getAbsolutePoiPptPathByTag(PptTag.TYPE_POI_REBUILD);
            String pptxPath = targetPath + no1PPT.getSrcDescription() + ".pptx";
            outputStream = new FileOutputStream(pptxPath);
            slideShow.write(outputStream);
            logger.info("------->  end!  result = " + ReturnCode.SUCCESS);
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR!   返回 FAILED");
            logger.error(e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                logger.error("------->  ERROR!   Finally Block Error");
                logger.error(e.getMessage());
            }
        }
        return ReturnCode.FAILED;
    }
}
