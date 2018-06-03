package com.parse.ppt.poi.controller;

import com.parse.ppt.poi.common.ReturnCode;
import com.parse.ppt.poi.entity.LoginInfoDTO;
import com.parse.ppt.poi.entity.User;
import com.parse.ppt.poi.service.UserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Jupiter
 * @version 2018/04/25 0:05
 */
@Controller
@RequestMapping("/login")
public class UserLoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserLoginService userLoginService;

    @Autowired
    public UserLoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    /**
     * 获取用户的在线状态，用户如果在线，返回 1 ；用户不在线，返回 0
     *
     * @param session HttpSession
     * @return 用户如果在线，返回 1 ；用户不在线，返回 0
     */
    @RequestMapping("/getUserLoginStatus")
    @ResponseBody
    public int getUserLoginStatus(HttpSession session) {
        if (logger.isDebugEnabled()) {
            logger.info("------->  start!");
        }
        int result = session.getAttribute("user") == null ? 0 : 1;
        if (logger.isDebugEnabled()) {
            logger.info("------->  end!  result = {}", result);
        }
        return result;
    }

    @RequestMapping("/userLogin")
    @ResponseBody
    public String userLogin(@RequestBody LoginInfoDTO loginInfoDTO,
                            HttpServletRequest request, HttpServletResponse response) {
        if (logger.isDebugEnabled()) {
            logger.info("------->  start!   LoginInfo = {}", loginInfoDTO);
        }
        String result = userLoginService.userLogin(loginInfoDTO, request, response);
        if (logger.isDebugEnabled()) {
            logger.info("------->  end!   result = {}", result);
        }
        return result;
    }

    @RequestMapping("/userRegister")
    @ResponseBody
    public String userRegister(@RequestBody User user, HttpSession session) {
        if (logger.isDebugEnabled()) {
            logger.info("------->  start!   user = {}", user);
        }
        String result = userLoginService.registerUser(user, session);
        if (logger.isDebugEnabled()) {
            logger.info("------->  end!   result = {}", result);
        }
        return result;
    }

    @RequestMapping("/userLogout")
    @ResponseBody
    public String userLogout(HttpSession session) {
        if (logger.isDebugEnabled()) {
            logger.info("------->  start! ");
        }
        session.removeAttribute("user");
        if (logger.isDebugEnabled()) {
            logger.info("------->  end!");
        }
        return ReturnCode.SUCCESS;
    }

    @RequestMapping("/getPublicKey")
    @ResponseBody
    public String getPublicKey(HttpSession session) {
        String sessionId = session.getId();
        if (logger.isDebugEnabled()) {
            logger.info("------->  start!   session ID = {}", sessionId);
        }
        String result = userLoginService.getPublicKey(sessionId);
        if (logger.isDebugEnabled()) {
            logger.info("------->  end!  publicKey = {}", result);
        }
        return result;
    }
}
