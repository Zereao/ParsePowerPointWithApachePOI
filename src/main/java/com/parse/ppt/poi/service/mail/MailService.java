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
     * 用户注册成功后，
     *
     * @param account 用户账号，可能是e-mail，也可能是手机号
     * @return 用户对象
     */
    String getUser(String account);
}
