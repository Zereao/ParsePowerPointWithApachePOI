package com.parse.ppt.poi.service.common.poi.operate.hslf.impl;

import com.parse.ppt.poi.common.PathUtil;
import com.parse.ppt.poi.common.PptTag;
import com.parse.ppt.poi.common.ReturnCode;
import com.parse.ppt.poi.dao.persistence.PoiPptDao;
import com.parse.ppt.poi.entity.No1PPT;
import com.parse.ppt.poi.entity.PoiPPT;
import com.parse.ppt.poi.service.common.poi.operate.hslf.PptOperateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFSlideShowImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;

@Service
public class PptOperateServiceImpl implements PptOperateService {
    private Logger logger = LogManager.getLogger(this.getClass());

    private final PoiPptDao poiPptDao;

    @Autowired
    public PptOperateServiceImpl(PoiPptDao poiPptDao) {
        this.poiPptDao = poiPptDao;
    }

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

    @Override
    public String rebuildPPT(No1PPT no1PPT, int[] adPageIndexs) {
        logger.info("------->  start!" +
                "   No1PPT no1PPT = " + no1PPT +
                "   adPageIndexs = " + Arrays.toString(adPageIndexs));
        OutputStream outputStream = null;
        try (
                InputStream pptFileInputStream = new FileInputStream(Objects.requireNonNull(PathUtil.getNo1PptFile(String.valueOf(no1PPT.getId()))));
                HSLFSlideShow slideShow = new HSLFSlideShow(new HSLFSlideShowImpl(pptFileInputStream))
        ) {
            if (adPageIndexs == null || adPageIndexs.length == 0) {
                logger.info("------->  end!  本地数据库中已经存在该PoiPPT，可以直接读取！ result = " + ReturnCode.RESOURCES_ALREADY_EXISTS);
                return ReturnCode.RESOURCES_ALREADY_EXISTS;
            } else if (adPageIndexs.length == 1 && adPageIndexs[0] == -1) {
                logger.info("------->  end!  未经OCR识别的PPT，得到的直接是No1PPT对象！  result = " + ReturnCode.UN_OCR);
                return ReturnCode.UN_OCR;
            }
            for (int adPageIndex : adPageIndexs) {
                slideShow.removeSlide(adPageIndex);
            }

            String targetPath = PathUtil.getAbsolutePoiPptPathByTag(PptTag.TYPE_POI_REBUILD);
            String pptPath = targetPath + no1PPT.getSrcDescription() + ".ppt";
            outputStream = new FileOutputStream(pptPath);
            slideShow.write(outputStream);
            PoiPPT ppt = new PoiPPT(no1PPT.getSrcDescription(), PptTag.TYPE_POI_REBUILD, no1PPT.getId());
            poiPptDao.addPoiPPT(ppt);
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
                logger.error("------->  ERROR!    Finally Block Error");
                logger.error(e.getMessage());
            }
        }
        return ReturnCode.FAILED;
    }


}
