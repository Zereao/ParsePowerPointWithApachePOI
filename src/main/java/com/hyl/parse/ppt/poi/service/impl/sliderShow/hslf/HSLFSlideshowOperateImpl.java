package com.hyl.parse.ppt.poi.service.impl.sliderShow.hslf;

import com.hyl.parse.ppt.poi.service.sildeshow.hslf.HSLFSlideshowOperate;
import org.apache.poi.hslf.usermodel.*;
import org.apache.poi.sl.usermodel.PictureData;

import java.awt.*;
import java.io.*;

/**
 * @author Jupiter
 */
public class HSLFSlideshowOperateImpl implements HSLFSlideshowOperate {
//    private static final String FILE_BATH_PATH = "";

    @Override
    public void createNewPPTFile(String pptName) throws IOException {
        HSLFSlideShow ppt = new HSLFSlideShow();
        //默认大小为 640 x 480
        ppt.setPageSize(new Dimension(640, 480));
        //创建第一页新的幻灯片
        ppt.createSlide();
        //把这些更改保存到文件中
        FileOutputStream out = new FileOutputStream(pptName + ".ppt");
        ppt.write(out);
        out.close();
    }

    @Override
    public void setSlideTitle(String pptName, String title) throws IOException {
        InputStream inputStream = new FileInputStream(new File(pptName + ".ppt"));
        HSLFSlideShow ppt = new HSLFSlideShow(inputStream);
        HSLFSlide slide = ppt.createSlide();
        HSLFTextBox titleBox = slide.addTitle();
        titleBox.setText(title);
        //保存修改
        FileOutputStream out = new FileOutputStream(pptName + ".ppt");
        ppt.write(out);
        out.close();
    }

    @Override
    public void getShapes(String pptName) {

    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public void extractAllPictures(String pptName) throws IOException {
        HSLFSlideShow ppt = new HSLFSlideShow(new HSLFSlideShowImpl(pptName + ".ppt"));
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
    public void addPicture(String pptName, String picName) throws IOException {
        HSLFSlideShow ppt = new HSLFSlideShow(new HSLFSlideShowImpl(pptName + ".ppt"));
        HSLFPictureData pictureData = ppt.addPicture(new File(picName + ".jpg"), PictureData.PictureType.JPEG);
        if (pictureData == null) {
            return;
        }
        HSLFPictureShape pictureNew = new HSLFPictureShape(pictureData);
        // 设置图片在幻灯页中的位置，这里默认是 左上角?
        pictureNew.setAnchor(new Rectangle(0, 0, 0, 0));
        // 创建了一张新的幻灯片
        HSLFSlide slide = ppt.createSlide();
        // 增加一个 shape，参数为之前设定好了的 HSLFPictureShape————pictureNew
        slide.addShape(pictureNew);
        //保存修改
        FileOutputStream out = new FileOutputStream(pptName + ".ppt");
        ppt.write(out);
        out.close();
    }

    @Override
    public void changeBackgroundOfSlide(String pptName, String picName) throws IOException {
        // 这个扩展名 .png 可以按需修改
        File backPic = new File(picName + ".png");
        HSLFSlideShow ppt = new HSLFSlideShow();
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
        FileOutputStream out = new FileOutputStream(pptName + ".ppt");
        ppt.write(out);
        out.close();
    }


}
