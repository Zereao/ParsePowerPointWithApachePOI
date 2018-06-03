package com.parse.ppt.poi.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * 使用 Java-Config来代替Spring-X.xml 进行配置
 *
 * @author Jupiter
 * @version 2018/05/28/10:49
 */
//@Configuration
public class SpringMailConfig {

    @Value("${mail.host}")
    private String smtpHost;
    @Value("${sender.username}")
    private String username;
    @Value("${sender.password}")
    private String password;
    @Value("${mail.smtp.auth}")
    private String smtpAuth;
    @Value("${mail.debug}")
    private String debug;
    @Value("${sender.email}")
    private String sender;


    @Bean(name = "mailSener")
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(smtpHost);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setDefaultEncoding("UTF-8");
        Properties javaMailProperties = new Properties();
        javaMailProperties.setProperty("mail.smtp.auth", smtpAuth);
        javaMailProperties.setProperty("mail.debug", debug);
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }

    @Bean
    public SimpleMailMessage mailMessage() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        return mailMessage;
    }


}
