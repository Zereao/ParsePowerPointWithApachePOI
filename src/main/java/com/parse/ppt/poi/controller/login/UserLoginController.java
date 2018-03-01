package com.parse.ppt.poi.controller.login;

import com.parse.ppt.poi.commom.ReturnCode;
import com.parse.ppt.poi.entity.User;
import com.parse.ppt.poi.service.cache.RedisCacheService;
import com.parse.ppt.poi.service.cookie.CookieService;
import com.parse.ppt.poi.service.encrypt.EncryptService;
import com.parse.ppt.poi.service.login.UserLoginService;
import com.parse.ppt.poi.service.mail.MailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Jupiter
 */
@Controller
@RequestMapping("/login")
public class UserLoginController {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private CookieService cookieService;
    @Autowired
    private MailService mailService;
    @Autowired
    private EncryptService encryptService;
    @Autowired
    private RedisCacheService redisCacheService;

    @RequestMapping("/loadUserFromCookies")
    @ResponseBody
    public String loadUserFromCookies(HttpSession session) {
        logger.info("UserLoginController.loadUserFromCookies   ------->  start! ");

        User user = cookieService.loadUserCookie();
        if (user == null) {
            logger.info("UserLoginController.loadUserFromCookies   ------->  end! ");
            return ReturnCode.FAILED;
        } else {
            session.setAttribute("user", user);
        }
        logger.info("UserLoginController.loadUserFromCookies   ------->  end!  user = " + user);
        return ReturnCode.SUCCESS;
    }

    @RequestMapping("/userLogin")
    @ResponseBody
    public String userLogin(@RequestParam("account") String account,
                            @RequestParam("password") String password,
                            @RequestParam("rememberTag") String rememberTag,
                            HttpSession session) {
        logger.info("UserLoginController.userLogin   ------->  start! " +
                "  account = " + account +
                "  password = " + password +
                "  rememberTag = " + rememberTag);
        // 根据账号获取到当前用户的信息
        User user = userLoginService.getUser(account);
        // 验证用户
        String result = userLoginService.verifyUser(user, password);
        // 如果密码校验通过
        if (result.equals(ReturnCode.SUCCESS)) {
            // 如果 前端用户点选了 1天内记住我
            if ("true".equals(rememberTag.toLowerCase())) {
                // 把用户名和密码保存到Cookie对象中
                result = cookieService.addUserCookie(user);
            }
            // 把该用户对象放在session中
            session.setAttribute("user", user);
        }
        logger.info("UserLoginController.userLogin   ------->  end! " +
                "  result = " + result);
        return result;
    }

    @RequestMapping("/userRegister")
    @ResponseBody
    public String userRegister(@RequestParam("username") String username,
                               @RequestParam("email") String email,
                               @RequestParam("phoneNum") String phoneNum,
                               @RequestParam("password") String password,
                               HttpSession session) {
        logger.info("UserLoginController.userRegister   ------->  start! " +
                "  username = " + username +
                "  email = " + email +
                "  phoneNum = " + phoneNum +
                "  password = " + password);

        User user = new User(username, email, phoneNum, password);
        String result = userLoginService.registerUser(user);
        // 如果注册成功，则将当前用户信息写入session
        if (ReturnCode.SUCCESS.equals(result)) {
            session.setAttribute("user", user);
            // 并且将用户信息发送到用户邮箱
            String subject = "nbsp;nbsp;nbsp;nbsp;亲爱的" + username + "，欢迎注册！<br>" +
                    "nbsp;nbsp;nbsp;nbsp;如果终有离别，请别辜负相遇。请享受接下来的愉快时光。";
            String content = "亲爱的" + username + "，您好！<br>" +
                    "下面是您注册的相关信息";
            result = mailService.sendSimpleWordMail(email, subject, content);
        }
        logger.info("UserLoginController.userRegister   ------->  end! " +
                " result = " + result);
        return result;
    }

    @RequestMapping("/userLogout")
    @ResponseBody
    public String userLogout(HttpSession session) {
        logger.info("UserLoginController.userLogout   ------->  start! ");

        User user = (User) session.getAttribute("user");
        String result = cookieService.removeUserCookie(user);
        session.removeAttribute("user");
        logger.info("UserLoginController.userLogout   ------->  end! " +
                " result = " + result);
        return result;
    }

    @RequestMapping("/getPublicKey")
    @ResponseBody
    public String getPublicKey() {
        logger.info("UserLoginController.getPublicKey   ------->  start! ");

        Map<String, String> resultMap = encryptService.getKeyPair();
        String publicKey = resultMap.get("publicKey");
        // 把公钥、秘钥都假如Redis高速缓存中
        String result = redisCacheService.addToRedisCache(resultMap);
        if (result.equals(ReturnCode.FAILED)) {
            logger.warn("添加公钥、密钥到Redis高速缓存失败！  result = " + result);
        }
        logger.info("UserLoginController.getPublicKey   ------->  end!  keyPair = " + resultMap);
        return publicKey;
    }
}
