package com.parse.ppt.poi.service.login.impl;

import com.parse.ppt.poi.common.ReturnCode;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Jupiter
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {
    private Logger logger = LogManager.getLogger(this.getClass());

    private final UserService userService;
    private final CookieService cookieService;
    private final MailService mailService;
    private final EncryptService encryptService;
    private final RedisCacheService redisCacheService;

    /**
     * 不推荐使用以下写法，而使用构造方法注入
     * <p>
     * 不推荐的示例：
     *
     * @Autowired private UserService userService;
     */
    @SuppressWarnings("JavaDoc")
    @Autowired
    public UserLoginServiceImpl(UserService userService, CookieService cookieService, MailService mailService, EncryptService encryptService, RedisCacheService redisCacheService) {
        this.userService = userService;
        this.cookieService = cookieService;
        this.mailService = mailService;
        this.encryptService = encryptService;
        this.redisCacheService = redisCacheService;
    }

    @Override
    public String userLogin(String account, String encryptedPassword, String rememberTag, HttpServletRequest request, HttpServletResponse response) {
        logger.info("------->  start! " +
                "  account = " + account +
                "  password = " + encryptedPassword +
                "  rememberTag = " + rememberTag);
        try {
            HttpSession session = request.getSession();
            User user = null;
            // 根据账号类型获取到当前用户的信息
            if (account.contains("@")) {
                user = userService.getUserByEmail(account);
            } else {
                user = userService.getUserByPhoneNum(account);
            }
            if (user == null) {
                return ReturnCode.ACCOUNT_NOT_EXISTS;
            }
            String result = ReturnCode.SUCCESS;
            // 登录页面请求登录时，点击按钮后，会先请求后端的公钥。请求的时候，会将请求得到的公钥-密钥对添加到Redis中缓存起来
            // 从Redis高速缓存中读取 公钥、密钥
            String sessionId = session.getId();
            String privateKey = redisCacheService.getByKey(sessionId + ".privateKey");
            // 前端传递过来的密码是加密后的密码，通过下面的方法得到用户真实的密码
            String realPassword = encryptService.contentDecrypter(privateKey, encryptedPassword);
            // 校验密码，如果不通过，返回 密码错误 错误码
            if (!(user.getPassword().equals(realPassword))) {
                logger.info("------->  end!   WRONG_PASSWORD");
                return ReturnCode.WRONG_PASSWORD;
            }
            logger.info("密码校验通过！开始接下来的操作！");
            // 如果密码校验通过,把当前用户的密码设置为使用当前密钥加密后的密码
            user.setPassword(encryptedPassword);
            // 如果 前端用户点选了 1天内记住我
            if ("true".equals(rememberTag.toLowerCase())) {
                // 把此时的用户对象添加进Cookies
                result = cookieService.addUserCookie(user, response);
                // 添加持久化的用户 公钥-密钥 对 到Redis 缓存中
                result = redisCacheService.addUserPersistentKeyPair(user.getUsername(), sessionId);
            }
            // 把加密了信息后的用户对象放在session中
            session.setAttribute("user", user);
            logger.info("------->  end! " +
                    "  result = " + result);
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error("------->  ERROR !");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String registerUser(User user, HttpSession session) {
        logger.info("------->  start! " +
                "  user = " + user);
        String result = ReturnCode.SUCCESS;
        try {
            String username = user.getUsername();
            String email = user.getEmail();
            String phoneNum = user.getPhoneNum();
            logger.info("------->  检查用户是否已经注册过 ");
            User userByEmail = userService.getUserByEmail(email);
            User userByPhoneNum = userService.getUserByPhoneNum(phoneNum);
            if (userByEmail != null || userByPhoneNum != null) {
                logger.info("------->  用户已经存在 ！");
                return ReturnCode.ACCOUNT_ALREADY_EXISTS;
            }
            logger.info("------->  检查完成，用户不存在于数据库中，可以注册 ");
            String sessionId = session.getId();
            String privateKey = redisCacheService.getByKey(sessionId + ".privateKey");
            String realPassword = encryptService.contentDecrypter(privateKey, user.getPassword());
            // 新创建一个密码解密后的User对象
            User decryptedUser = new User(username, email, phoneNum, realPassword);
            result = userService.addUser(decryptedUser);
            // 如果注册成功，则将当前用户信息写入session
            if (ReturnCode.SUCCESS.equals(result)) {
                result = redisCacheService.addUserPersistentKeyPair(username, sessionId);
                session.setAttribute("user", user);
                // 并且将用户信息发送到用户邮箱
                String subject = "nbsp;nbsp;nbsp;nbsp;亲爱的" + username + "，欢迎注册！<br>" +
                        "nbsp;nbsp;nbsp;nbsp;如果终有离别，请别辜负相遇。请享受接下来的愉快时光。";
                String content = "亲爱的" + username + "，您好！<br>" +
                        "下面是您注册的相关信息<br><br>" +
                        decryptedUser.toString();
                result = mailService.sendSimpleWordMail(email, subject, content);
                logger.info("------->  end! " +
                        "  result = " + result);
            }
        } catch (Exception e) {
            logger.error("------->  ERROR !");
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public String userLogout(HttpServletRequest request, HttpServletResponse response) {
        logger.info("------->  start! ");
        try {
            request.getSession().removeAttribute("user");
            String result = cookieService.removeUserCookie(request, response);
            logger.info("------->  end!  SUCCESS");
            return result;
        } catch (Exception e) {
            logger.error("------->  ERROR !");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String getPublicKey(String sessionId) {
        logger.info("------->  start! " +
                "  session id = " + sessionId);
        try {
            Map<String, String> resultMap = encryptService.getKeyPair(sessionId);
            String publicKey = resultMap.get(sessionId + ".publicKey");
            // 把公钥、秘钥都加入Redis高速缓存中
            String result = redisCacheService.addToRedisCache(resultMap);
            if (result.equals(ReturnCode.FAILED)) {
                logger.warn("添加公钥-密钥 map 到Redis高速缓存失败！  resultMap = " + resultMap);
            }
            logger.info("------->  end!  keyPair = " + resultMap);
            return publicKey;
        } catch (Exception e) {
            logger.error("------->  ERROR !");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }

    @Override
    public String getUserLoginStatus(HttpSession session) {
        logger.info("------->  start!");
        try {
            String result = null;
            if (session.getAttribute("user") != null) {
                result = "true";
            } else {
                result = "false";
            }
            logger.info("------->  start!" +
                    "   result = " + result);
            return result;
        } catch (Exception e) {
            logger.error("------->  ERROR !");
            logger.error(e.getMessage());
        }
        return ReturnCode.FAILED;
    }
}
