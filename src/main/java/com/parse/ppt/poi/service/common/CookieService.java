package com.parse.ppt.poi.service.common;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 当用户登录时选则了 一天内记住我 时，添加本地Cookie
 *
 * @author Jupiter
 * @version 2018/05/26 11:32
 */
@Service
public interface CookieService {

    /**
     * 添加用户Cookie信息
     *
     * @param user     用户对象，将其的部分属性保存Cookie
     * @param response HttpServletResponse对象
     * @param <T>      泛型
     * @return true OR false，成功 OR 失败
     */
    <T> boolean addCookie(T user, HttpServletResponse response);

    /**
     * 移除用户的Cookie信息
     *
     * @param userClass 用户对象的Class对象
     * @param request   HttpServletRequest request
     * @param response  HttpServletResponse response
     * @param <T>       泛型
     * @return true OR false，成功 OR 失败
     */
    <T> boolean removeCookie(Class<T> userClass, HttpServletRequest request, HttpServletResponse response);

    /**
     * 读取用户Cookie信息
     *
     * @param request HttpServletRequest对象
     * @return 返回码
     */
    /**
     * 从Cookie中读取用户的信息
     *
     * @param userClass 用户的对象的Class对象
     * @param request   HttpServletRequest
     * @param <T>       泛型
     * @return 读取的用户对象
     */
    <T> T loadCookie(Class<T> userClass, HttpServletRequest request);
}
