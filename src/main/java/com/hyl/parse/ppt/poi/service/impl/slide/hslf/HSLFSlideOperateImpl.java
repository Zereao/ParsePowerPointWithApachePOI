package com.hyl.parse.ppt.poi.service.impl.slide.hslf;

import com.hyl.parse.ppt.poi.service.slide.hslf.HSLFSlideOperate;
import org.apache.poi.hslf.usermodel.HSLFShape;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;

import java.io.FileOutputStream;
import java.io.IOException;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

import static com.hyl.parse.ppt.poi.service.common.CommonUtil.PPT_BATH_PATH;
import static com.hyl.parse.ppt.poi.service.common.CommonUtil.loadPPT;

/**
 * @author Jupiter
 */
public class HSLFSlideOperateImpl implements HSLFSlideOperate {
    @Override
    public String retrieveSlideSize(String pptName) {
        HSLFSlideShow ppt = loadPPT(pptName);
        java.awt.Dimension pgSize = ppt.getPageSize();
        //幻灯片宽度
        int pgX = pgSize.width;
        //幻灯片高度
        int pgY = pgSize.height;
        return "长：" + pgX + "；宽：" + pgY;
    }

    @Override
    public void resizeSlideSize(String pptName, int width, int height) throws IOException {
        HSLFSlideShow ppt = loadPPT(pptName);
        ppt.setPageSize(new Dimension(width, height));
        FileOutputStream out = new FileOutputStream(PPT_BATH_PATH + pptName + "[" + width + "x" + height + "].ppt");
        ppt.write(out);
        out.close();
    }

    @Override
    public Map<String, Rectangle2D> getShapes(String pptName) {
        HSLFSlideShow ppt = loadPPT(pptName);
        Map<String, Rectangle2D> shapesInfo = new HashMap<>();
        for (HSLFSlide slide : ppt.getSlides()) {
            for (HSLFShape shape : slide.getShapes()) {
                // shapeName
                String shapeName = shape.getShapeName();
                // shapes's anchor which defines the position of this shape in the slide
                Rectangle2D anchor = shape.getAnchor();
                shapesInfo.put(shapeName, anchor);
            }
        }
        return shapesInfo;
    }
}
