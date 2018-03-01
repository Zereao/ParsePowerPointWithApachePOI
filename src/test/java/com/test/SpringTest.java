package com.test;

import com.parse.ppt.poi.dao.cache.RedisCacheDao;
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
@ContextConfiguration({"classpath:spring/spring.xml", "classpath:spring/spring-redis.xml"})
public class SpringTest {

    @Autowired
    private RedisCacheDao redisCacheDao;

    @Test
    public void testU() {
        redisCacheDao.add("1213", null);
        String result = redisCacheDao.get("1213");
        System.out.println("++++++++++++++++++++++++++++++++++++++");
        System.out.println(".get(1213) = " + result);

//        redisCacheDao.add("1213", "123123123");
//        String result = redisCacheDao.get("1213");
//        System.out.println(result);
//        result = redisCacheDao.remove("1213");
//        System.out.println(result);
    }


}
