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
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 这个类专门用来处理数据
 *
 * @author Jupiter
 * @version 2018/05/30/20:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@PropertySource(value = "classpath:properties/db_config.properties")
@ContextConfiguration(classes = {SpringConfig.class})
public class JavaConfigTest {
    @Autowired
    Environment environment;

    @Test
    public void test() {
        System.out.println(environment.getRequiredProperty("mysql.driverClass"));
    }
}
