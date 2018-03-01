package com.parse.ppt.poi.service.common.user;

import com.parse.ppt.poi.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author Jupiter
 * @date 2018/03/01/17:59
 */
@Service
public interface UserService {
    /**
     * 根据email获取用户对象
     *
     * @param email 用户 email
     * @return 用户对象
     */
    User getUserByEmail(String email);

    /**
     * 根据手机号码获取用户信息
     *
     * @param phoneNum 用户 手机号码
     * @return 用户对象
     */
    User getUserByPhoneNum(String phoneNum);
}
