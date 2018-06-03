package com.parse.ppt.poi.service;

import com.parse.ppt.poi.common.ReturnCode;
import com.parse.ppt.poi.dao.RedisCacheDao;
import com.parse.ppt.poi.dao.UserDao;
import com.parse.ppt.poi.entity.LoginInfoDTO;
import com.parse.ppt.poi.entity.User;
import com.parse.ppt.poi.service.common.CookieService;
import com.parse.ppt.poi.service.common.EncryptService;
import com.parse.ppt.poi.service.common.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Jupiter
 * @version 2018/03/28 16:59
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserDao userDao;
    private final CookieService cookieService;
    private final MailService mailService;
    private final EncryptService encryptService;
    private final RedisCacheDao redisCacheDao;

    @Autowired
    public UserLoginServiceImpl(CookieService cookieService, MailService mailService, EncryptService encryptService, UserDao userDao, RedisCacheDao redisCacheDao) {
        this.cookieService = cookieService;
        this.mailService = mailService;
        this.encryptService = encryptService;
        this.userDao = userDao;
        this.redisCacheDao = redisCacheDao;
    }

    @Override
    public String userLogin(LoginInfoDTO loginInfoDTO, HttpServletRequest request, HttpServletResponse response) {
        logger.info("------->  start!   loginInfoDTO = {}", loginInfoDTO);
        try {
            HttpSession session = request.getSession();
            // 从loginInfoDTO中获取到用户登录的相关信息
            String account = loginInfoDTO.getAccount();
            String encryptedPassword = loginInfoDTO.getPassword();
            String rememberTag = loginInfoDTO.getRememberTag();
            // 根据账号类型获取到当前用户的信息
            User userOfDB = null;
            if (account.contains("@")) {
                userOfDB = userDao.getUserByEmail(account);
            } else {
                userOfDB = userDao.getUserByMobile(account);
            }
            if (userOfDB == null) {
                return ReturnCode.ACCOUNT_NOT_EXISTS;
            }
            String result = ReturnCode.SUCCESS;
            // 登录页面请求登录时，点击按钮后，会先请求后端的公钥。请求的时候，会将请求得到的公钥-密钥对添加到Redis中缓存起来
            // 从Redis高速缓存中读取 公钥、密钥
            String sessionId = session.getId();
            String privateKey = redisCacheDao.get(sessionId + ".privateKey");
            // 前端传递过来的密码是加密后的密码，通过下面的方法得到用户真实的密码
            String realPassword = encryptService.contentDecrypter(privateKey, encryptedPassword);
            // 校验密码，如果不通过，返回 密码错误 错误码
            if (!(userOfDB.getPassword().equals(realPassword))) {
                logger.info("------->  end!   WRONG_PASSWORD");
                return ReturnCode.WRONG_PASSWORD;
            }
            logger.info("密码校验通过！开始接下来的操作！");
            // 如果密码校验通过,把当前用户的密码设置为使用当前密钥加密后的密码
            userOfDB.setPassword(encryptedPassword);
            // 如果 前端用户点选了 1天内记住我
            if ("true".equals(rememberTag.toLowerCase())) {
                // 把此时的用户对象添加进Cookies
                boolean addCookieResult = cookieService.addCookie(userOfDB, response);
            }
            // 获取持久化的用户 公钥-密钥 对，并添加到Redis 缓存中
            Map<String, String> newKeyPairMap = encryptService.getKeyPair(userOfDB.getUsername());
            result = redisCacheDao.addUserPersistentKeyPair(sessionId, newKeyPairMap);
            // 把加密了信息后的用户对象放在session中
            session.setAttribute("user", userOfDB);
            logger.info("------->  end!   result = {}", result);
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR !");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String registerUser(User user, HttpSession session) {
        logger.info("------->  start!   user = {}", user);
        String result = ReturnCode.SUCCESS;
        try {
            String username = user.getUsername();
            String email = user.getEmail();
            String mobile = user.getMobile();
            logger.info("------->  检查用户是否已经注册过 ");
            User userByEmail = userDao.getUserByEmail(email);
            User userByPhoneNum = userDao.getUserByMobile(mobile);
            if (userByEmail != null || userByPhoneNum != null) {
                logger.info("------->  用户[{}]已经存在 ！", userByEmail != null ? userByEmail : userByPhoneNum);
                return ReturnCode.ACCOUNT_ALREADY_EXISTS;
            }
            logger.info("------->  检查完成，用户不存在于数据库中，可以注册 ");
            String sessionId = session.getId();
            String privateKey = redisCacheDao.get(sessionId + ".privateKey");
            String realPassword = encryptService.contentDecrypter(privateKey, user.getPassword());
            // 新创建一个密码解密后的User对象
            User decryptedUser = new User();
            decryptedUser.setUsername(username);
            decryptedUser.setEmail(email);
            decryptedUser.setMobile(mobile);
            decryptedUser.setPassword(realPassword);

            userDao.addUser(decryptedUser);
            // 如果注册成功，则将当前用户信息写入session
            User realPassUser = userDao.getUserByEmail(user.getEmail());
            session.setAttribute("user", realPassUser);

            // 获取持久化的用户 公钥-密钥 对，并添加到Redis 缓存中
            Map<String, String> newKeyPairMap = encryptService.getKeyPair(username);
            result = redisCacheDao.addUserPersistentKeyPair(sessionId, newKeyPairMap);

            // 并且将用户信息发送到用户邮箱
            String subject = "&emsp;&emsp;亲爱的" + username + "，欢迎注册！<br>" +
                    "&emsp;&emsp;如果终有离别，请别辜负相遇。请享受接下来的愉快时光。";
            String content = "亲爱的" + username + "，您好！<br>" +
                    "下面是您注册的相关信息<br><br>" +
                    decryptedUser.toString();
            result = mailService.sendSimpleWordMail(email, subject, content);
            logger.info("------->  end!   result = {}", result);
        } catch (Exception e) {
            logger.error("------->  ERROR !");
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public String getPublicKey(String sessionId) {
        logger.info("------->  start!   sessionId = {}", sessionId);
        try {
            Map<String, String> resultMap = encryptService.getKeyPair(sessionId);
            String publicKey = resultMap.get(sessionId + ".publicKey");
            // 把公钥、秘钥都加入Redis高速缓存中
            String result = redisCacheDao.add(resultMap);
            if (result.equals(ReturnCode.FAILED)) {
                logger.warn("添加公钥-密钥 Map 到Redis高速缓存失败！  resultMap = {}", resultMap);
            }
            logger.info("------->  end!   keyPair = {}", resultMap);
            return publicKey;
        } catch (Exception e) {
            logger.error("------->  ERROR !");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }
}
