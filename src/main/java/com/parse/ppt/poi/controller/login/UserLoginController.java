package com.parse.ppt.poi.controller.login;

import com.parse.ppt.poi.entity.User;
import com.parse.ppt.poi.service.login.UserLoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Jupiter
 */
@Controller
@RequestMapping("/login")
public class UserLoginController {
    private Logger logger = LogManager.getLogger(this.getClass());

    private final UserLoginService userLoginService;

    @Autowired
    public UserLoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @RequestMapping("/userLogin")
    @ResponseBody
    public String userLogin(@RequestParam("account") String account,
                            @RequestParam("password") String encryptedPassword,
                            @RequestParam("rememberTag") String rememberTag,
                            HttpServletRequest request, HttpServletResponse response) {
        logger.info("------->  start! " +
                "  account = " + account +
                "  password = " + encryptedPassword +
                "  rememberTag = " + rememberTag);
        String result = userLoginService.userLogin(account, encryptedPassword, rememberTag, request, response);
        logger.info("------->  end! " +
                "  result = " + result);
        return result;
    }

    @RequestMapping("/userRegister")
    @ResponseBody
    public String userRegister(@RequestParam("username") String username,
                               @RequestParam("email") String email,
                               @RequestParam("phoneNum") String phoneNum,
                               @RequestParam("password") String encryptedPassword,
                               HttpSession session) {
        logger.info("------->  start! " +
                "  username = " + username +
                "  email = " + email +
                "  phoneNum = " + phoneNum +
                "  password = " + encryptedPassword);

        User user = new User(username, email, phoneNum, encryptedPassword);
        String result = userLoginService.registerUser(user, session);
        logger.info("------->  end! " +
                " result = " + result);
        return result;
    }

    @RequestMapping("/userLogout")
    @ResponseBody
    public String userLogout(HttpServletRequest request, HttpServletResponse response) {
        logger.info("------->  start! ");
        String result = userLoginService.userLogout(request, response);
        logger.info("------->  end! " +
                " result = " + result);
        return result;
    }

    @RequestMapping("/getPublicKey")
    @ResponseBody
    public String getPublicKey(HttpSession session) {
        String sessionId = session.getId();
        logger.info("------->  start! " +
                "   session ID = " + sessionId);
        String result = userLoginService.getPublicKey(sessionId);
        logger.info("------->  end!  publicKey = " + result);
        return result;
    }
}
