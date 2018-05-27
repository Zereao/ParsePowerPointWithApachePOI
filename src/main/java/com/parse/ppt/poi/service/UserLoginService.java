package com.parse.ppt.poi.service;

import com.parse.ppt.poi.entity.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Jupiter
 * 用户登录页面的相关逻辑处理 Service
 */
@Service
public interface UserLoginService {
    /**
     * 用户登录方法
     *
     * @param account           用户账号
     * @param encryptedPassword 前端传递过来的被加密了的密码
     * @param rememberTag       是否记住用户一天的标记
     * @param request           HttpServletRequest对象
     * @param response          HttpServletResponse对象
     * @return 返回码
     */
    String userLogin(String account, String encryptedPassword, String rememberTag, HttpServletRequest request, HttpServletResponse response);

    /**
     * 用户注册
     *
     * @param user    前端传过来的注册用户对象，密码被加密
     * @param session HttpSession 对象
     * @return 返回信息
     */
    String registerUser(User user, HttpSession session);

    /**
     * 用户注销登录
     *
     * @param request  HttpServletRequest request对象
     * @param response HttpServletResponse response对象
     * @return 返回信息
     */
    String userLogout(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取 publicKey
     *
     * @param sessionId sessionId
     * @return publicKey
     */
    String getPublicKey(String sessionId);

    /**
     * 获取用户的登陆状态，即获取session中是否存在 user 属性
     *
     * @param session HttpSession对象
     * @return true-存在用户对象<p>false-不存在用户对象<p>返回的是字符串 true/false
     */
    String getUserLoginStatus(HttpSession session);
}
