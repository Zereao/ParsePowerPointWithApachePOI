package com.parse.ppt.poi.service.common;

import com.parse.ppt.poi.common.PathUtil;
import com.parse.ppt.poi.common.ReturnCode;
import com.parse.ppt.poi.dao.No1PptDao;
import com.parse.ppt.poi.dao.PoiPptDao;
import com.parse.ppt.poi.entity.No1PPT;
import com.parse.ppt.poi.entity.PoiPPT;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFSlideShowImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final No1PptDao no1PptDao;
    private final PoiPptDao poiPptDao;

    @Autowired
    public PptOperateServiceImpl(PoiPptDao poiPptDao, No1PptDao no1PptDao) {
        this.poiPptDao = poiPptDao;
        this.no1PptDao = no1PptDao;
    }


    @Override
    public int ppt2img(No1PPT no1PPT) {
        logger.info("------->  start!    no1PPT = {}", no1PPT);
        String no1pptId = String.valueOf(no1PPT.getId());
        File ppt = PathUtil.getNo1PptFile(no1pptId);
        String ppt2ImgPath = PathUtil.getNo1Ppt2ImgPath(no1pptId);
        int pageNum = ppt2img(ppt, ppt2ImgPath);
        logger.info("------->  end!    pageNum = {}", pageNum);
        return pageNum;
    }

    @Override
    public int ppt2img(PoiPPT poiPPT) {
        logger.info("------->  start!    poiPPT = {}", poiPPT);
        String no1pptId = String.valueOf(poiPPT.getNo1pptId());
        File ppt = PathUtil.getPoiPptFile(no1pptId);
        String ppt2ImgPath = PathUtil.getPoiPpt2imgPath(no1pptId);
        int pageNum = ppt2img(ppt, ppt2ImgPath);
        logger.info("------->  end!    pageNum = {}", pageNum);
        return pageNum;
    }


    @Override
    public String rebuildPPT(No1PPT no1PPT, int[] adPageIndexs) {
//        logger.info("------->  start!" +
//                "   No1PPT no1PPT = " + no1PPT +
//                "   adPageIndexs = " + Arrays.toString(adPageIndexs));
//        OutputStream outputStream = null;
//        try (
//                InputStream pptFileInputStream = new FileInputStream(Objects.requireNonNull(PathUtil.getNo1PptFile(String.valueOf(no1PPT.getId()))));
//                HSLFSlideShow slideShow = new HSLFSlideShow(new HSLFSlideShowImpl(pptFileInputStream))
//        ) {
//            if (adPageIndexs == null || adPageIndexs.length == 0) {
//                logger.info("------->  end!  本地数据库中已经存在该PoiPPT，可以直接读取！ result = " + ReturnCode.RESOURCES_ALREADY_EXISTS);
//                return ReturnCode.RESOURCES_ALREADY_EXISTS;
//            } else if (adPageIndexs.length == 1 && adPageIndexs[0] == -1) {
//                logger.info("------->  end!  未经OCR识别的PPT，得到的直接是No1PPT对象！  result = " + ReturnCode.UN_OCR);
//                return ReturnCode.UN_OCR;
//            }
//            for (int adPageIndex : adPageIndexs) {
//                slideShow.removeSlide(adPageIndex);
//            }
//
//            String targetPath = PathUtil.getAbsolutePoiPptPathByTag(PptTag.TYPE_POI_REBUILD);
//            String pptPath = targetPath + no1PPT.getSrcDescription() + ".ppt";
//            outputStream = new FileOutputStream(pptPath);
//            slideShow.write(outputStream);
//            PoiPPT ppt = new PoiPPT(no1PPT.getSrcDescription(), PptTag.TYPE_POI_REBUILD, no1PPT.getId());
//            poiPptDao.addPoiPPT(ppt);
//            logger.info("------->  end!  result = " + ReturnCode.SUCCESS);
//            return ReturnCode.SUCCESS;
//        } catch (Exception e) {
//            logger.error("------->  ERROR!   返回 FAILED");
//            logger.error(e.getMessage());
//        } finally {
//            try {
//                if (outputStream != null) {
//                    outputStream.close();
//                }
//            } catch (IOException e) {
//                logger.error("------->  ERROR!    Finally Block Error");
//                logger.error(e.getMessage());
//            }
//        }
        return ReturnCode.FAILED;
    }

    /**
     * 私有的PPT转图片的方法
     *
     * @param pptFile    PPT文件
     * @param targetPath 图片保存文件夹
     * @return 图片张数
     */
    private int ppt2img(File pptFile, String targetPath) {
        if (logger.isDebugEnabled()) {
            logger.info("------->  start!   pptFile = {}    targetPath = {}", pptFile.getAbsolutePath(), targetPath);
        }
        try (
                FileInputStream inputStream = new FileInputStream(pptFile);
                HSLFSlideShow ppt = new HSLFSlideShow(inputStream)
        ) {
            // 如果输出目录不存在，则创建
            PathUtil.mkDirsIfNotExists(targetPath);
            Dimension pageSize = ppt.getPageSize();
            // 图片被命名为 1.png , 2.png , 3.png
            int pageNum = 1;
            for (HSLFSlide slide : ppt.getSlides()) {
                BufferedImage img = new BufferedImage(pageSize.width, pageSize.height, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = img.createGraphics();
                // clear the drawing area
                graphics.setPaint(Color.white);
                graphics.fill(new Rectangle2D.Float(0, 0, pageSize.width, pageSize.height));
                // render
                slide.draw(graphics);
                // save the output
                String filename = targetPath + pageNum + ".png";
                FileOutputStream out = new FileOutputStream(filename);
                javax.imageio.ImageIO.write(img, "png", out);
                out.close();
                pageNum++;
            }
            if (logger.isDebugEnabled()) {
                logger.info("------->  end! pageNum = {}", pageNum);
            }
            return pageNum;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 FAILED ");
            logger.error(e.getMessage());
        }
        return -1;
    }
}
