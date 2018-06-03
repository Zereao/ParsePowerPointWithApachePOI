package com.test;

import com.parse.ppt.poi.dao.No1PptDao;
import com.parse.ppt.poi.dao.PoiPptDao;
import com.parse.ppt.poi.entity.No1PPT;
import com.parse.ppt.poi.service.No1PptService;
import com.parse.ppt.poi.service.common.FileDownloadService;
import com.parse.ppt.poi.service.common.PoiService;
import com.parse.ppt.poi.service.common.PptxOperateService;
import com.parse.ppt.poi.service.common.SpiderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Jupiter
 * @date 2018/03/01/12:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test_spring/spring.xml",
        "classpath:test_spring/spring-mail.xml",
//        "classpath:test_spring/spring-mvc.xml",
        "classpath:test_spring/spring-mybatis.xml",
        "classpath:test_spring/spring-redis.xml"})
public class SpringTest {

    @Autowired
    private SpiderService spiderService;
    @Autowired
    private No1PptService no1PptService;
    @Autowired
    private FileDownloadService fileDownloadService;
    @Autowired
    private PptxOperateService pptxOperateService;
    @Autowired
    private No1PptDao no1PptDao;
    @Autowired
    private PoiService poiService;
    @Autowired
    private PoiPptDao poiPptDao;

    private String result = null;

    @Test
    public void test1() throws InterruptedException {
        result = fileDownloadService.downloadPptImgssSync();

    }

    @Test
    public void testU() throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 340 * 4; i < 340 * 5; i++) {
                    result = no1PptService.ppt2img(String.valueOf(i));
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 340; i++) {
                    result = no1PptService.ppt2img(String.valueOf(i));
                }
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 340; i < 340 * 2; i++) {
                    if (i == 461) {
                        continue;
                    }
                    result = no1PptService.ppt2img(String.valueOf(i));
                }
            }
        });
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 340 * 2; i < 340 * 3; i++) {
                    result = no1PptService.ppt2img(String.valueOf(i));
                }
            }
        });
        Thread thread5 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 340 * 3; i < 340 * 4; i++) {
                    result = no1PptService.ppt2img(String.valueOf(i));
                }
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        Thread.sleep(3600 * 1000);
    }




    @Test
    public void test6() {
        No1PPT no1PPT = no1PptDao.getNo1PPTById(1);
        int[] a = new int[]{5, 11};
        poiService.rebuildPPT(no1PPT, a);
    }

}
