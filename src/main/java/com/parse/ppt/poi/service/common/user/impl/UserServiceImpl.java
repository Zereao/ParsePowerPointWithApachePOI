package com.parse.ppt.poi.service.common.user.impl;

import com.parse.ppt.poi.commom.ReturnCode;
import com.parse.ppt.poi.dao.persistence.UserDao;
import com.parse.ppt.poi.entity.User;
import com.parse.ppt.poi.service.common.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jupiter
 * @date 2018/03/01/17:59
 */
@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByEmail(String email) {
        try {
            logger.info("UserServiceImpl.getUserByEmail()   ------->  start!  email = " + email);
            User user = userDao.getUserByEmail(email);
            logger.info("UserServiceImpl.getUserByEmail()   ------->  theUser = " + user);
            return user;
        } catch (Exception e) {
            logger.error("UserServiceImpl.getUserByEmail()   ------->  ERROR!  返回 null ");
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public User getUserByPhoneNum(String phoneNum) {
        try {
            logger.info("UserServiceImpl.getUserByPhoneNum()   ------->  start!  phoneNum = " + phoneNum);
            User user = userDao.getUserByPhoneNum(phoneNum);
            logger.info("UserServiceImpl.getUserByPhoneNum()   ------->  theUser = " + user);
            return user;
        } catch (Exception e) {
            logger.error("UserServiceImpl.getUserByPhoneNum()   ------->  ERROR!  返回 null ");
            logger.error(e.getMessage());
        }
        return null;
    }
}
