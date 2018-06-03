package com.parse.ppt.poi.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

/**
 * @author 何雨伦
 * @version 2018/05/31/11:50
 */
@Configuration
@PropertySource(value = {
        "classpath:properties/db_config.properties",
        "classpath:properties/mail_config.properties",
        "classpath:properties/redis_config.properties"
})
@ComponentScan(basePackages = {"com.parse.ppt.poi"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)})
public class SpringConfig {
    public SpringConfig() {
        System.out.println("----------> SpringConfig  构造方法");
    }
}
