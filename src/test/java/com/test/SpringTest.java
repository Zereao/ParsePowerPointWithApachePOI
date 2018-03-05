package com.test;

import com.parse.ppt.poi.dao.cache.RedisCacheDao;
import com.parse.ppt.poi.service.common.no1ppt.No1PptService;
import com.parse.ppt.poi.service.common.spider.WebSpiderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Jupiter
 * @date 2018/03/01/12:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-mybatis.xml"})
public class SpringTest {

    @Autowired
    private WebSpiderService webSpiderService;

    @Test
    public void testU() {
        for (int i = 1; i < 80; i++) {
            webSpiderService.pptFileSpider(String.valueOf(i));
        }
    }


}
