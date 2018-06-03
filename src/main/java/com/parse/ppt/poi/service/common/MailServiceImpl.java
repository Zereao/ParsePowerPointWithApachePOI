package com.parse.ppt.poi.service.common;

import com.parse.ppt.poi.common.ReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author Jupiter
 * @version 2018/02/27/12:00
 */
@Service
public class MailServiceImpl implements MailService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JavaMailSender mailSender;
    private final SimpleMailMessage mailMessage;

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender, SimpleMailMessage mailMessage) {
        this.mailSender = mailSender;
        this.mailMessage = mailMessage;
    }

    @Override
    public String sendSimpleWordMail(String emailTo, String subject, String content) {
        logger.info("------->  start!   emailTo = {}    subject =    content = {}", emailTo, subject, content);
        try {
            mailMessage.setTo(emailTo);
            mailMessage.setSubject(subject);
            mailMessage.setText(content);
            mailSender.send(mailMessage);
            logger.info("------->  end!   result = {}", ReturnCode.SUCCESS);
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR !");
            logger.error(e.getMessage());
        }
        return ReturnCode.MAIL_SEND_FAILED;
    }

    @Override
    public String sendSimpleWordMail(String emailTo, String emailCc, String subject, String content) {
        logger.info("------->  start!   emailTo = {}   emailCc = {}   subject = {}   content = {}", emailTo, emailCc, subject, content);
        try {
            mailMessage.setTo(emailTo);
            mailMessage.setCc(emailCc);
            mailMessage.setSubject(subject);
            mailMessage.setText(content);
            mailSender.send(mailMessage);
            logger.info("------->  end!   result = {}", ReturnCode.SUCCESS);
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR !");
            logger.error(e.getMessage());
        }
        return ReturnCode.MAIL_SEND_FAILED;
    }
}
