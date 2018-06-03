package com.op;

import com.parse.ppt.poi.configuration.SpringConfig;
import com.parse.ppt.poi.configuration.SpringMaBatisConfig;
import com.parse.ppt.poi.configuration.SpringMailConfig;
import com.parse.ppt.poi.configuration.SpringRedisConfig;
import com.parse.ppt.poi.dao.No1PptDao;
import com.parse.ppt.poi.entity.No1PPT;
import com.parse.ppt.poi.service.common.SpiderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 这个类专门用来处理数据
 *
 * @author Jupiter
 * @version 2018/05/30/20:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, SpringMaBatisConfig.class, SpringMailConfig.class, SpringRedisConfig.class})
public class OperationUtil {
    @Autowired
    private SpiderService spiderService;
    @Autowired
    private No1PptDao no1PptDao;

    @Autowired
    private Environment environment;

    @Test
    public void test1() {
        String a = environment.getRequiredProperty("mysql.driverClass");
        System.out.println(a);
    }

    @Test
    public void grabNo1pptInfo() {
        for (int i = 1; i < 101; i++) {
            List<No1PPT> no1PPTList = spiderService.pptFileSpider(String.valueOf(i));
            if (no1PPTList == null || no1PPTList.size() == 0) {
                continue;
            }
            for (No1PPT no1PPT : no1PPTList) {
                no1PptDao.addNo1PPT(no1PPT);
            }
        }
    }
}
