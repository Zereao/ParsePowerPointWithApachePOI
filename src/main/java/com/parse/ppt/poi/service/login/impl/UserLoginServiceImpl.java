package com.parse.ppt.poi.service.login.impl;

import com.parse.ppt.poi.commom.ReturnCode;
import com.parse.ppt.poi.dao.UserDao;
import com.parse.ppt.poi.entity.User;
import com.parse.ppt.poi.service.cache.RedisCacheService;
import com.parse.ppt.poi.service.encrypt.EncryptService;
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
    @Autowired
    private EncryptService encryptService;
    @Autowired
    private RedisCacheService redisCacheService;

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
        String result;
        // 从Redis高速缓存中读取 公钥、密钥
        String publicKey = redisCacheService.getByKey("publicKey");
        String privateKey = redisCacheService.getByKey("privateKey");
        // 读取完公钥密钥后，从Redis中 移除 当前的公钥、密钥
        result = redisCacheService.removeFromRedis("publicKey");
        if (result.equals(ReturnCode.FAILED)) {
            logger.warn("redisCacheService.removeFromRedis(publicKey) FAILED" + publicKey);
        }
        result = redisCacheService.removeFromRedis("privateKey");
        if (result.equals(ReturnCode.FAILED)) {
            logger.warn("redisCacheService.removeFromRedis(privateKey) FAILED" + privateKey);
        }
        // 前端传递过来的密码是加密后的密码，通过下面的方法得到用户真实的密码
        String realPassword = encryptService.contentDecrypter(publicKey, privateKey, password);
        assert user != null;
        if (!(user.getPassword().equals(realPassword))) {
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
