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
     * 根据账号获取用户对象
     *
     * @param account 用户账号，可能是e-mail，也可能是手机号
     * @return 用户对象
     */
    User getUser(String account);

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
