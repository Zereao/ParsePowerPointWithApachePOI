package com.parse.ppt.poi.service.login.impl;

import com.parse.ppt.poi.commom.ReturnCode;
import com.parse.ppt.poi.dao.UserDao;
import com.parse.ppt.poi.entity.User;
import com.parse.ppt.poi.service.login.UserLoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jupiter
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(String account) {
        logger.info("UserLoginServiceImpl.getUser   ------->  start! " +
                "  account = " + account);
        String result = ReturnCode.SUCCESS;
        User user = null;
        if (account.contains("@")) {
            try {
                logger.info("userDao.getUserByEmail()   ------->  start!  email = " + account);
                user = userDao.getUserByEmail(account);
                logger.info("after userDao.getUserByEmail()   ------->  theUser = " + user);
            } catch (Exception e) {
                logger.error(e.getMessage());
                result = ReturnCode.FAILED;
            }
        } else {
            try {
                logger.info("userDao.getUserByPhoneNum()   ------->  start!  phoneNum = " + account);
                user = userDao.getUserByPhoneNum(account);
                logger.info("userDao.getUserByPhoneNum()   ------->  end!  theUser = " + user);
            } catch (Exception e) {
                logger.error(e.getMessage());
                result = ReturnCode.FAILED;
            }
        }
        logger.info("UserLoginServiceImpl.getUser   ------->  end! " +
                "  result = " + result +
                "  theUser = " + user);
        return user;
    }

    @Override
    public String verifyUser(User user, String password) {
        logger.info("UserLoginServiceImpl.verifyUser   ------->  start! " +
                "  user = " + user +
                "  password = " + password);

        String result = ReturnCode.SUCCESS;
        assert user != null;
        if (!(user.getPassword().equals(password))) {
            result = ReturnCode.WRONG_PASSWORD;
        }
        logger.info("UserLoginServiceImpl.verifyUser   ------->  end! " +
                "  result = " + result);
        return result;
    }


    @Override
    public String registerUser(User user) {
        logger.info("UserLoginServiceImpl.registerUser   ------->  start! " +
                "  user = " + user);
        String result = ReturnCode.SUCCESS;
        try {
            logger.info("UserLoginServiceImpl.registerUser   ------->  检查用户是否已经注册过 ");
            User userByEmail = userDao.getUserByEmail(user.getEmail());
            User userByPhoneNum = userDao.getUserByPhoneNum(user.getPhoneNum());
            if (userByEmail != null || userByPhoneNum != null) {
                logger.info("UserLoginServiceImpl.registerUser   ------->  用户已经存在 ！");
                return ReturnCode.ACCOUNT_ALREADY_EXISTS;
            }
            logger.info("UserLoginServiceImpl.registerUser   ------->  检查完成，用户不存在于数据库中，可以注册 ");
            logger.info("userDao.addUser   ------->  start! user = " + user);
            userDao.addUser(user);
            logger.info("userDao.addUser   ------->  end! ");
        } catch (Exception e) {
            logger.error(e.getMessage());
            result = ReturnCode.FAILED;
        }
        logger.info("UserLoginServiceImpl.registerUser   ------->  end! " +
                "  result = " + result);
        return result;
    }

}
