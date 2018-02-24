package com.hyl.parse.ppt.poi.service.login.impl;

import com.hyl.parse.ppt.poi.dao.login.UserLoginDao;
import com.hyl.parse.ppt.poi.service.login.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService{

    @Autowired
    private UserLoginDao userLoginDao;

    @Override
    public String verifyUser(String account) {
        if ("@".equals(account)){
            return verifyUserByEmail(account);
        } else {
            return verifyUserByPhoneNum(account);
        }
    }


    /**
     * 根据Email来验证用户
     * @param email 用户email
     * @return 返回信息
     */
    private String verifyUserByEmail(String email){
        return null;
    }

    /**
     * 根据手机号码来验证用户
     * @param phoneNum 用户手机号
     * @return 返回信息
     */
    private String verifyUserByPhoneNum(String phoneNum){
        return null;
    }

}
