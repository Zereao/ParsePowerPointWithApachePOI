package com.parse.ppt.poi.service.common;

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

    /**
     * 调用dao层向数据库中增加一条用户信息
     *
     * @param user user对象
     * @return 返回码
     */
    String addUser(User user);

    /**
     * 更新用户首页文章
     *
     * @param email        用户email
     * @param essayTitle   用户首页-文章标题
     * @param essayContent 用户首页-文章内容
     * @return 返回码
     */
    String updateUserEssay(String email, String essayTitle, String essayContent);
}
