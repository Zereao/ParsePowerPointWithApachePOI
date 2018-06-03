package com.parse.ppt.poi.service;

import com.parse.ppt.poi.entity.LoginInfoDTO;
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
     * @param loginInfoDTO 用户登录信息DTO
     * @param request      HttpServletRequest对象
     * @param response     HttpServletResponse对象
     * @return 返回码
     */
    String userLogin(LoginInfoDTO loginInfoDTO, HttpServletRequest request, HttpServletResponse response);

    /**
     * 用户注册
     *
     * @param user    前端传过来的注册用户对象，密码被加密
     * @param session HttpSession 对象
     * @return 返回信息
     */
    String registerUser(User user, HttpSession session);

    /**
     * 获取 publicKey
     *
     * @param sessionId sessionId
     * @return publicKey
     */
    String getPublicKey(String sessionId);
}
