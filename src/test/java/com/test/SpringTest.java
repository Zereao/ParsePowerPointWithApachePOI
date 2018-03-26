package com.test;

import com.parse.ppt.poi.dao.persistence.No1PptDao;
import com.parse.ppt.poi.entity.No1PPT;
import com.parse.ppt.poi.service.common.cache.RedisCacheService;
import com.parse.ppt.poi.service.no1ppt.No1PptService;
import com.parse.ppt.poi.service.common.spider.WebSpiderService;
import com.parse.ppt.poi.service.common.download.FileDownloadService;
import com.parse.ppt.poi.service.common.poi.xslf.PptxOperateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private No1PptDao no1PptDao;

    private String result = null;

    @Test
    public void test1() throws InterruptedException {
        result = fileDownloadService.downloadPptImgssSync();
        Thread.sleep(24 * 3600 * 1000);
    }

    @Test
    public void testU() {
        result = no1PptService.ppt2img("7");

    }

    @Test
    public void test3() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test
    public void test4() {
        List<No1PPT> result = no1PptDao.getNo1PPTByKeyWordFuzzy("红色");
        for (No1PPT no1PPT : result) {
            System.out.println(no1PPT.getSrcDescription());
        }
    }

    @Test
    public void test5() {
        List<String> keywordsList = new ArrayList<>();
        keywordsList.add("红色");
        keywordsList.add("运动");
        keywordsList.add("艺术");
        keywordsList.add("简洁");
        keywordsList.add("方块");

        Set<No1PPT> no1PPTSet = no1PptService.getNo1PPTByKeyWordsRelevancy(keywordsList);
        for (No1PPT no1PPT : no1PPTSet) {
            System.out.println(no1PPT.getSrcDescription());
        }
    }
}
