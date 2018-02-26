package com.parse.ppt.poi.service.cookie.impl;

import com.parse.ppt.poi.commom.ReturnCode;
import com.parse.ppt.poi.entity.User;
import com.parse.ppt.poi.service.cookie.CookieService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jupiter
 */
@Service
public class CookieServiceImpl implements CookieService {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public String addUserCookie(User user) {
        logger.info("CookieServiceImpl.addUserCookie   ------->  start! " +
                "  user = " + user);
        // 通过Spring提供的RequestContextHolder在非contrller层获取request和response对象
        try {
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            String username = user.getUsername();
            String email = user.getEmail();
            String phoneNum = user.getPhoneNum();
            String password = user.getPassword();
            Cookie usernameCookie = new Cookie("username", username);
            Cookie emailCookie = new Cookie("email", email);
            Cookie phoneNumCookie = new Cookie("phoneNum", phoneNum);
            Cookie passwordCookie = new Cookie("password", password);
            List<Cookie> cookies = new ArrayList<>();
            cookies.add(usernameCookie);
            cookies.add(emailCookie);
            cookies.add(phoneNumCookie);
            cookies.add(passwordCookie);
            for (Cookie cookie : cookies) {
                // 单位 为 秒
                cookie.setMaxAge(24 * 3600);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            logger.info("CookieServiceImpl.addUserCookie   ------->  end!   SUCCESS!");
            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.info("CookieServiceImpl.addUserCookie   ------->  end!   FAILED!");
        }
        return ReturnCode.FAILED;
    }
}
