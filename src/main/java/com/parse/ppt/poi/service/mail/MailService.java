package com.parse.ppt.poi.service.mail;

import org.springframework.stereotype.Service;

/**
 * 用于发送邮件的类
 *
 * @author Jupiter
 * @date 2018/02/27/12:00
 */
@Service
public interface MailService {
    /**
     * 用户注册成功后，将用户注册信息发送到用户的电子邮箱信中      亦可作为用户验证
     *
     * @param email   用户邮箱地址
     * @param content 邮件文本内容
     * @return 返回码
     */
    String sendSimpleWordMail(String email, String content);
}
