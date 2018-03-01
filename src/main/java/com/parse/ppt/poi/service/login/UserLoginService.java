package com.parse.ppt.poi.service.login;

import com.parse.ppt.poi.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author Jupiter
 * 用户登录页面的相关逻辑处理 Service
 */
@Service
public interface UserLoginService {
    /**
     * 从Cookie中获取用户对象
     *
     * @return 用户对象
     */
    User loadUserFromCookies();

    /**
     * 用户登录方法
     *
     * @param account           用户账号
     * @param encryptedPassword 前端传递过来的被加密了的密码
     * @param rememberTag       是否记住用户一天的标记
     * @return 返回码
     */
    String userLogin(String account, String encryptedPassword, String rememberTag);


    /**
     * 验证用户信息是否正确
     *
     * @param user     前端传递过来的 account对应的用户对象
     * @param password 前端传递过来的 password
     * @return 返回信息
     */
    String verifyUser(User user, String password);

    /**
     * 用户注册
     *
     * @param user 注册用户对象
     * @return 返回信息
     */
    String registerUser(User user);
}
