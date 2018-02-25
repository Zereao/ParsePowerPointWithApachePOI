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
     * 验证用户信息是否正确
     *
     * @param account 用户账号，可能是e-mail，也可能是手机号
     * @return 返回信息
     */
    String verifyUser(String account, String password);

    /**
     * 用户注册
     *
     * @param user 注册用户对象
     * @return 返回信息
     */
    String registerUser(User user);
}
