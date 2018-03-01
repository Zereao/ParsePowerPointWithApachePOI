package com.parse.ppt.poi.service.common.cookie;

import com.parse.ppt.poi.entity.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jupiter
 * 当用户登录时选则了 一天内记住我 时，添加本地Cookie
 */
@Service
public interface CookieService {

    /**
     * 添加用户Cookie信息
     *
     * @param user     用户对象，将其的部分属性保存Cookie
     * @param response HttpServletResponse对象
     * @return 返回码
     */
    String addUserCookie(User user, HttpServletResponse response);

    /**
     * 移除用户Cookie信息
     *
     * @param request  HttpServletRequest request
     * @param response HttpServletResponse response
     * @return 返回码
     */
    String removeUserCookie(HttpServletRequest request, HttpServletResponse response);

    /**
     * 读取用户Cookie信息
     *
     * @param request HttpServletRequest对象
     * @return 返回码
     */
    User loadUserCookie(HttpServletRequest request);
}
