package com.parse.ppt.poi.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author Jupiter
 * @version 2018/05/28/17:56
 */
//@Configuration
//@PropertySource(value = "classpath:properties/db_config.properties")
public class SpringMaBatisConfig {

    @Value("${mysql.driverClass}")
    private String ceshi;

    @Autowired
    Environment env;

    @Bean
    public DataSource dataSource() {
        System.out.println("###########################################################################################");
        System.out.println("@Value = " + ceshi);
        System.out.println("###########################################################################################");
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(env.getRequiredProperty("mysql.driverClass"));
        basicDataSource.setUrl(env.getRequiredProperty("mysql.jdbcUrl"));
        basicDataSource.setUsername(env.getRequiredProperty("mysql.username"));
        basicDataSource.setPassword(env.getRequiredProperty("mysql.password"));
        basicDataSource.setInitialSize(Integer.parseInt(env.getRequiredProperty("ds.initialSize")));
        basicDataSource.setMaxTotal(Integer.parseInt(env.getRequiredProperty("ds.maxTotal")));
        basicDataSource.setMaxIdle(Integer.parseInt(env.getRequiredProperty("ds.maxIdle")));
        basicDataSource.setMinIdle(Integer.parseInt(env.getRequiredProperty("ds.minIdle")));
        basicDataSource.setMaxWaitMillis(Long.parseLong(env.getRequiredProperty("ds.maxWaitMillis")));
        return basicDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        return sqlSessionFactory;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.parse.ppt.poi.dao");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

}
