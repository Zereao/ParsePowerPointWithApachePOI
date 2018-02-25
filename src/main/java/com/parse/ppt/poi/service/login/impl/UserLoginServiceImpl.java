package com.parse.ppt.poi.service.login.impl;

import com.parse.ppt.poi.dao.UserDao;
import com.parse.ppt.poi.entity.User;
import com.parse.ppt.poi.service.login.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private UserDao userDao;

    @Override
    public String verifyUser(String account, String password) {
        User user = null;
        if ("@".equals(account)) {
            user = userDao.getUserByEmail(account);

        } else {
//            return verifyUserByPhoneNum(account, password);
        }
        return null;
    }

}
