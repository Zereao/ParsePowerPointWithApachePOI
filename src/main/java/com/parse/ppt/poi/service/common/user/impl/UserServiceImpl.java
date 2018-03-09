package com.parse.ppt.poi.service.common.user.impl;

import com.parse.ppt.poi.common.ReturnCode;
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

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            logger.info("------->  start!  email = " + email);
            User user = userDao.getUserByEmail(email);
            logger.info("------->  theUser = " + user);
            return user;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 null ");
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public User getUserByPhoneNum(String phoneNum) {
        try {
            logger.info("------->  start!  phoneNum = " + phoneNum);
            User user = userDao.getUserByPhoneNum(phoneNum);
            logger.info("------->  theUser = " + user);
            return user;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 null ");
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public String addUser(User user) {
        try {
            logger.info("------->  start!  user = " + user);
            userDao.addUser(user);
            logger.info("------->  end ! SUCCESS");
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 FAILED ");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String updateUserEssay(String email, String essayTitle, String essayContent) {
        try {
            logger.info("------->  start!" +
                    "   email = " + email +
                    "   essayTitle = " + essayTitle);
            userDao.updateUserEssay(email, essayTitle, essayContent);
            logger.info("------->  end ! SUCCESS");
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR!  返回 FAILED ");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }
}
