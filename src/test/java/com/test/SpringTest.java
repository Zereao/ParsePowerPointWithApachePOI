package com.test;

import com.parse.ppt.poi.dao.persistence.No1PptDao;
import com.parse.ppt.poi.entity.No1PPT;
import com.parse.ppt.poi.service.common.cache.RedisCacheService;
import com.parse.ppt.poi.service.common.spider.WebSpiderService;
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
public class SpringTest {

    @Autowired
    private WebSpiderService webSpiderService;
    @Autowired
    private No1PptDao no1PptDao;
    @Autowired
    private RedisCacheService redisCacheService;

    @Test
    public void testU() {
        String essayContent = "As you slowly open your eyes, look around, Notice where the light comes into your room; Listen carefully, See if there are new sounds you can recognize; Feel with your body and spirit and see if you can sense the freshness in the air.\n" +
                "Yes, yes, yes, it's a new day! it's a different day and it's a bright day! And most importantly, it's a new beginning for your life, a beginning where you are going to make new decisions; take new actions, make new friends and take your life to a totally unprecedented level!\n" +
                "In your mind s eye you can see clearly the things you want to have, the places you intend to go the relationships you desire to develop and the positions you aspire to reach. You can hear your laughter’s of joy and happiness on the day when everything happens as you dream; you can see the smiles on the people around you when the magic moment strikes. You can feel your face is getting red your heart is beating fast, and your blood is rushing all over your body to every single corner of your being!\n" +
                "You know all this is real as long as you are confident passionate and committed! And you are confident you are passionate and you are committed! You will no longer fear making new sounds, showing new facial expressions using your body in new ways approaching new people and asking new questions! You will live every single day of your life with absolute passion and you will show your passion through the words you speak and the actions you take! You will focus all your time and effort on the most important goals of your life! You will never succumb to challenges of hardships! You will never waver in your pursuit of excellence! After all, you are the best! and you deserve the best!\n" +
                "As your coach and friend I can assure you the door to all the best things in the world will open to you, but the key to that door is in your hand. You must do your part, you must faithfully follow the plans you make and take the actions you plan, you must never quit, you must never fear! I know you must do it! You can do it! You will do it! And you will succeed! Now stand firm and tall make a fist get excited, and yell it out:\n" +
                "I must do it! I can do it! I will do it! I will succeed!\n" +
                "I must do it! I can do it! I will do it! I will succeed!\n" +
                "I must do it! I can do it! I will do it! I will succeed!";
        redisCacheService.addToRedisCache("essayTitle", "As you slowly open your eyes");
        redisCacheService.addToRedisCache("essayContent", essayContent);
    }


}
