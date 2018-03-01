package com.parse.ppt.poi.service.common.mail.impl;

import com.parse.ppt.poi.commom.ReturnCode;
import com.parse.ppt.poi.service.common.mail.MailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author Jupiter
 * @date 2018/02/27/12:00
 */
@Service
public class MailServiceImpl implements MailService {
    private Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    @Qualifier("mailMessage")
    private SimpleMailMessage mailMessage;

    // fuck 163 !! 网易邮箱SB，发送时需要抄送自己一份，否则报错 554 DT:SPM
    @Value("${sender.email}")
    private String sender;

    @Override
    public String sendSimpleWordMail(String emailTo, String subject, String content) {
        logger.info("MailServiceImpl.sendSimpleWordMail   ------->  start! " +
                "  emailTo = " + emailTo +
                "  subject = " + subject +
                "  content = " + content +
                "  sender = " + sender);
        try {
            mailMessage.setTo(emailTo);
            mailMessage.setSubject(subject);
            mailMessage.setText(content);
            mailSender.send(mailMessage);
            logger.info("MailServiceImpl.sendSimpleWordMail   ------->  end!   result =" + ReturnCode.SUCCESS);
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.info("MailServiceImpl.sendSimpleWordMail   ------->  end!   result =" + ReturnCode.MAIL_SEND_FAILED);
        }
        return ReturnCode.MAIL_SEND_FAILED;
    }

    @Override
    public String sendSimpleWordMail(String emailTo, String emailCc, String subject, String content) {
        logger.info("MailServiceImpl.sendSimpleWordMail  the Overload Method (Contains mailMessage.setCc() ) ------->  start! " +
                "  emailTo = " + emailTo +
                "  emailCc = " + emailCc +
                "  subject = " + subject +
                "  content = " + content +
                "  sender = " + sender);
        try {
            mailMessage.setTo(emailTo);
            mailMessage.setCc(emailCc);
            mailMessage.setSubject(subject);
            mailMessage.setText(content);
            mailSender.send(mailMessage);
            logger.info("MailServiceImpl.sendSimpleWordMail  the Overload Method (Contains mailMessage.setCc() ) ------->  end! " +
                    "  result =" + ReturnCode.SUCCESS);
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.info("MailServiceImpl.sendSimpleWordMail  the Overload Method (Contains mailMessage.setCc() ) ------->  end! " +
                    "  result =" + ReturnCode.MAIL_SEND_FAILED);
        }
        return ReturnCode.MAIL_SEND_FAILED;
    }
}
