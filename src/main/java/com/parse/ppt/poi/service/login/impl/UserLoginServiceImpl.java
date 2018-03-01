package com.parse.ppt.poi.service.login.impl;

import com.parse.ppt.poi.commom.ReturnCode;
import com.parse.ppt.poi.dao.persistence.UserDao;
import com.parse.ppt.poi.entity.User;
import com.parse.ppt.poi.service.common.cache.RedisCacheService;
import com.parse.ppt.poi.service.common.cookie.CookieService;
import com.parse.ppt.poi.service.common.encrypt.EncryptService;
import com.parse.ppt.poi.service.common.user.UserService;
import com.parse.ppt.poi.service.login.UserLoginService;
import com.parse.ppt.poi.service.common.mail.MailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Jupiter
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private CookieService cookieService;
    @Autowired
    private MailService mailService;
    @Autowired
    private EncryptService encryptService;
    @Autowired
    private RedisCacheService redisCacheService;

    @Override
    public User loadUserFromCookies() {
        logger.info("UserLoginServiceImpl.loadUserFromCookies   ------->  start! ");

        User user = null;
        try {
            user = cookieService.loadUserCookie();
            logger.info("UserLoginServiceImpl.loadUserFromCookies   ------->  end!  user = " + user);
            return user;
        } catch (Exception e) {
            logger.info("UserLoginServiceImpl.loadUserFromCookies   ------->  ERROR!  " + e.getMessage());
        }
        return null;
    }

    @Override
    public String userLogin(String account, String encryptedPassword, String rememberTag) {
        User user = null;
        // 根据账号类型获取到当前用户的信息
        if ("@".equals(account)) {
            user = userService.getUserByEmail(account);
        } else {
            user = userService.getUserByPhoneNum(account);
        }
        if (user == null) {
            return ReturnCode.ACCOUNT_NOT_EXISTS;
        }
        String result;
        // 登录页面请求登录时，点击按钮后，会先请求后端的公钥。请求的时候，会将请求得到的公钥密钥对添加到Redis中缓存起来
        // reids中 当前用户 的 公钥 对应的 key
        String theKeyOfPublicKey = user.getUsername() + ".publicKey";
        // reids中 当前用户 的 密钥 对应的 key
        String theKeyOfPrivateKey = user.getUsername() + ".privateKey";

        // 从Redis高速缓存中读取 公钥、密钥
        String publicKey = redisCacheService.getByKey(theKeyOfPublicKey);
        String privateKey = redisCacheService.getByKey(theKeyOfPrivateKey);
        // 读取完公钥密钥后，从Redis中 移除 当前的公钥、密钥
        redisCacheService.removeFromRedis(theKeyOfPublicKey);
        redisCacheService.removeFromRedis(theKeyOfPrivateKey);
        // 前端传递过来的密码是加密后的密码，通过下面的方法得到用户真实的密码
        String realPassword = encryptService.contentDecrypter(publicKey, privateKey, encryptedPassword);
        // 校验密码，如果不通过
        if (!(user.getPassword().equals(realPassword))) {
            return ReturnCode.WRONG_PASSWORD;
        }
        // 如果密码校验通过
        // 如果 前端用户点选了 1天内记住我
        if ("true".equals(rememberTag.toLowerCase())) {
            // 把用户名和密码保存到Cookie对象中
            result = cookieService.addUserCookie(user);
        }
        // 通过Spring提供的RequestContextHolder在非contrller层获取request和response对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        // 把该用户对象放在session中
        session.setAttribute("user", user);
        logger.info("UserLoginController.userLogin   ------->  end! " +
                "  result = " + result);
    }


    @Override
    public String verifyUser(User user, String password) {
        logger.info("UserLoginServiceImpl.verifyUser   ------->  start! " +
                "  user = " + user +
                "  password = " + password);

        return null;
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
