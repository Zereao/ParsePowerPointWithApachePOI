package com.test;

import com.parse.ppt.poi.dao.persistence.No1PptDao;
import com.parse.ppt.poi.entity.No1PPT;
import com.parse.ppt.poi.service.common.spider.WebSpiderService;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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
public class No1PPTSpriderTestDemo {

    @Autowired
    private WebSpiderService webSpiderService;
    @Autowired
    private No1PptDao no1PptDao;

    @Test
    public void testU() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 1; i < 22; i++) {
                List<No1PPT> resultMapList = webSpiderService.pptFileSpider(String.valueOf(i));
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 22; i < 44; i++) {
                List<No1PPT> resultMapList = webSpiderService.pptFileSpider(String.valueOf(i));
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 44; i < 66; i++) {
                List<No1PPT> resultMapList = webSpiderService.pptFileSpider(String.valueOf(i));
            }
        });
        Thread thread4 = new Thread(() -> {
            for (int i = 66; i < 89; i++) {
                List<No1PPT> resultMapList = webSpiderService.pptFileSpider(String.valueOf(i));
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        Thread.sleep(24 * 3600 * 1000);
    }


}
