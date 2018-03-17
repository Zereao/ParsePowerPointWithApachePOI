package com.test;

import com.parse.ppt.poi.dao.persistence.No1PptDao;
import com.parse.ppt.poi.entity.No1PPT;
import com.parse.ppt.poi.service.common.cache.RedisCacheService;
import com.parse.ppt.poi.service.common.spider.WebSpiderService;
import com.parse.ppt.poi.service.download.FileDownloadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class SpringTest {

    @Autowired
    private WebSpiderService webSpiderService;
    @Autowired
    private No1PptDao no1PptDao;
    @Autowired
    private RedisCacheService redisCacheService;
    @Autowired
    private FileDownloadService fileDownloadService;

    @Test
    public void testU() {
        List<String> list = webSpiderService.BaiduPicSpider(1, "ppt");
        fileDownloadService.downloadBaiduImg(list);
    }


}
