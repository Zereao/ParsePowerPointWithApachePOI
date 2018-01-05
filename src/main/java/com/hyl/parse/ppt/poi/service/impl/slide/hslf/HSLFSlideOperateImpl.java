package com.hyl.parse.ppt.poi.service.impl.slide.hslf;

import com.hyl.parse.ppt.poi.service.slide.hslf.HSLFSlideOperate;
import org.apache.poi.hslf.usermodel.*;
import org.apache.poi.sl.usermodel.PictureData;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hyl.parse.ppt.poi.service.common.CommonUtil.IMAGE_BATH_PATH;
import static com.hyl.parse.ppt.poi.service.common.CommonUtil.getPicExt;


/**
 * @author Jupiter
 */
public class HSLFSlideOperateImpl implements HSLFSlideOperate {
    @Override
    public void setSlideTitle(HSLFSlideShow ppt, String title) {
        HSLFSlide slideshow = ppt.createSlide();
        HSLFTextBox titleBox = slideshow.addTitle();
        titleBox.setText(title);
        //保存修改
    }

    @SuppressWarnings({"unchecked", "MismatchedQueryAndUpdateOfCollection"})
    @Override
    public List<Map<String, Rectangle2D>> getShapes(HSLFSlide slide) {
        List<Map<String, Rectangle2D>> shapesMapList = new ArrayList<>();
        for (HSLFShape shape : slide.getShapes()) {
            // shapeName
            String shapeName = shape.getShapeName();
            // shapes's anchor which defines the position of this shape in the slide
            Rectangle2D anchor = shape.getAnchor();
            shapesMapList.add((Map<String, Rectangle2D>) new HashMap<>().put(shapeName, anchor));
        }
        return shapesMapList;
    }

    @Override
    public Map<Integer, List<Map<String, Rectangle2D>>> getAllShapes(HSLFSlideShow ppt) {
        Map<Integer, List<Map<String, Rectangle2D>>> resultMap = new HashMap<>();
        List<HSLFSlide> slideList = ppt.getSlides();
        for (int i = 0; i < slideList.size(); i++) {
            resultMap.put(i, getShapes(slideList.get(i)));
        }
        return resultMap;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public void extractAllPictures(HSLFSlideShow ppt) throws IOException {
        int index = 1;
        File dir = new File("图片输出");
        if (dir.isDirectory() && !dir.exists()) {
            dir.mkdir();
        }
        for (HSLFPictureData picture : ppt.getPictureData()) {
            byte[] date = picture.getData();
            /* 我估计 import org.apache.poi.sl.usermodel.PictureData;  对应着 HSLF
                而            org.apache.poi.ss.usermodel.PictureData   对应着  HSSF
             */
            PictureData.PictureType type = picture.getType();
            String ext = type.extension;
            FileOutputStream out = new FileOutputStream("图片输出/pict_" + index + ext);
            out.write(date);
            out.close();
            index++;
        }
    }

    @Override
    public void addPicture(HSLFSlideShow ppt, String picName) throws IOException {
        String picExt = getPicExt(picName);
        File picFile = new File(IMAGE_BATH_PATH + picName + picExt);
        HSLFPictureData pictureData = ppt.addPicture(picFile, PictureData.PictureType.JPEG);
        if (pictureData == null) {
            return;
        }
        HSLFPictureShape pictureNew = new HSLFPictureShape(pictureData);
        // 设置图片在幻灯页中的位置，这里默认是 左上角?
        pictureNew.setAnchor(new Rectangle(0, 0, 0, 0));
        // 创建了一张新的幻灯片
        HSLFSlide slideshow = ppt.createSlide();
        // 增加一个 shape，参数为之前设定好了的 HSLFPictureShape————pictureNew
        slideshow.addShape(pictureNew);
        //保存修改
//        new CommonUtil().saveChange(pptName, ppt);
    }

    @Override
    public void changeBackgroundOfSlide(HSLFSlideShow ppt, String picName) throws IOException {
        // 这个扩展名 .png 可以按需修改
        File backPic = new File(picName + ".png");
        // 这个slide有它自己的背景
        HSLFSlide slide = ppt.createSlide();

        //如果没有下面的代码，它将使用 模板背景    下面这个方法的作用，猜测是  是否使用 默认的背景
        slide.setFollowMasterBackground(false);
        HSLFFill fill = slide.getBackground().getFill();
        // 注意是否需要修改第二个参数
        HSLFPictureData pictureData = ppt.addPicture(backPic, PictureData.PictureType.PNG);
        fill.setFillType(HSLFFill.FILL_PATTERN);
        fill.setPictureData(pictureData);
        //保存修改
//        new CommonUtil().saveChange(pptName, ppt);
    }

    @Override
    public void changeBackgroundOfShape(HSLFSlideShow ppt) {

    }


}
