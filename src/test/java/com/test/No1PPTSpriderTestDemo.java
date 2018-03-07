package com.test;

import com.parse.ppt.poi.dao.persistence.No1PptDao;
import com.parse.ppt.poi.service.common.spider.WebSpiderService;
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
public class No1PPTSpriderTestDemo {

    @Autowired
    private WebSpiderService webSpiderService;
    @Autowired
    private No1PptDao no1PptDao;

    @Test
    public void testU() {
        for (int i = 1; i < 87; i++) {
            webSpiderService.pptFileSpider(String.valueOf(i));
        }
    }


}
