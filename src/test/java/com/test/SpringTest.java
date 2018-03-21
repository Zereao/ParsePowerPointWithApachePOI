package com.test;

import com.parse.ppt.poi.service.common.cache.RedisCacheService;
import com.parse.ppt.poi.service.no1ppt.No1PptService;
import com.parse.ppt.poi.service.common.spider.WebSpiderService;
import com.parse.ppt.poi.service.common.unzip.UnzipService;
import com.parse.ppt.poi.service.common.download.FileDownloadService;
import com.parse.ppt.poi.service.poi.xslf.PptxOperateService;
import com.parse.ppt.poi.service.ppt2img.No1Ppt2imgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

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
    private WebSpiderService webSpiderService;
    @Autowired
    private No1PptService no1PptService;
    @Autowired
    private RedisCacheService redisCacheService;
    @Autowired
    private FileDownloadService fileDownloadService;
    @Autowired
    private PptxOperateService pptxOperateService;
    @Autowired
    private No1Ppt2imgService no1Ppt2imgService;

    private String result = null;

    @Test
    public void test1() throws InterruptedException {
        result = no1PptService.downloadAllNo1PPTSync();
        Thread.sleep(24 * 3600 * 1000);
    }

    @Test
    public void testU() {
        for (int i = 1; i < 5; i++) {
            no1Ppt2imgService.ppt2img(String.valueOf(i));
        }

    }

    @Test
    public void test3() throws InterruptedException {
        Thread.sleep(1000);
    }

}
