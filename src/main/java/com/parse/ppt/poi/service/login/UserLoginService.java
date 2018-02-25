package com.parse.ppt.poi.service.login;

import org.springframework.stereotype.Service;

/**
 * @author Jupiter
 * 用户登录页面的相关逻辑处理 Service
 */
@Service
public interface UserLoginService {
    /**
     * 验证用户信息是否正确
     * @param account 用户账号，可能是e-mail，也可能是手机号
     * @return 返回信息
     */
    String verifyUser(String account,String password);

    /**
     * 根据手机号码来验证用户
     * @param phoneNum 用户手机号
     * @return 返回信息
     */
    String registerUser(String phoneNum);
}
