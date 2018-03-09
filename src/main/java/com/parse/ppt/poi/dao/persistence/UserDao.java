package com.parse.ppt.poi.dao.persistence;

import com.parse.ppt.poi.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Jupiter
 */
@Repository
public interface UserDao {
    /**
     * 通过email获取用户信息
     *
     * @param email 用户电子邮箱
     * @return User
     */
    User getUserByEmail(String email);

    /**
     * 通过手机号码获取用户信息
     *
     * @param phoneNum 用户手机号码
     * @return User
     */
    User getUserByPhoneNum(String phoneNum);

    /**
     * 增加用户
     *
     * @param user 增加的用户对象
     */
    void addUser(User user);

    /**
     * 更新用户首页文章
     *
     * @param email        用户email
     * @param essayTitle   用户首页-文章标题
     * @param essayContent 用户首页-文章内容
     */
    void updateUserEssay(@Param("email") String email,
                         @Param("essayTitle") String essayTitle,
                         @Param("essayContent") String essayContent);


    /*  以下的逻辑并未在mapper中实现  +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */

    /**
     * 更新用户信息
     *
     * @param user 用户对象
     */
    void updateUser(User user);

    /**
     * 通过email删除用户
     *
     * @param email 用户电子邮箱
     * @return User
     */
    int deleteUserByEmail(String email);

    /**
     * 通过手机号码删除用户
     *
     * @param phoneNum 用户手机号码
     * @return User
     */
    int deleteUserByPhoneNum(String phoneNum);
}
