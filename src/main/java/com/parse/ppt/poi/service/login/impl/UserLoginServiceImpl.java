package com.parse.ppt.poi.service.login.impl;

import com.parse.ppt.poi.dao.UserDao;
import com.parse.ppt.poi.entity.User;
import com.parse.ppt.poi.service.login.UserLoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    private Logger logger = LogManager.getLogger(UserLoginServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public String verifyUser(String account, String password) {
        logger.info("UserLoginServiceImpl.verifyUser   ------->  start! " +
                "  account = " + account +
                "  password = " + password);

        String result = "SUCCESS";
        User user = null;
        if (account.contains("@")) {
            try {
                logger.info("userDao.getUserByEmail()" + account);
                user = userDao.getUserByEmail(account);
                logger.info("after userDao.getUserByEmail()   theUser = " + user);
            } catch (Exception e) {
                logger.error(e.getMessage());
                result = "FAILED";
            }
        } else {
            try {
                user = userDao.getUserByPhoneNum(account);
            } catch (Exception e) {
                logger.error(e.getMessage());
                result = "FAILED";
            }
        }
        assert user != null;
        if (!(user.getPassword().equals(password))) {
            result = "FAILED";
        }
        logger.info("UserLoginServiceImpl.verifyUser   ------->  end! " +
                "  result = " + result);
        return result;
    }

    @Override
    public String registerUser(User user) {
        logger.info("UserLoginServiceImpl.registerUser   ------->  start! " +
                "  user = " + user.toString());
        String result = "SUCCESS";
        try {
            userDao.addUser(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            result = "FAILED!";
        }
        logger.info("UserLoginServiceImpl.registerUser   ------->  end! " +
                "  result = " + result);
        return result;
    }

}
