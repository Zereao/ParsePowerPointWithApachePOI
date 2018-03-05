package com.parse.ppt.poi.dao.persistence;

import com.parse.ppt.poi.entity.No1PPT;

/**
 * @author Jupiter
 * @date 2018/03/05/19:06
 */
public interface No1PptDao {
    /**
     * 通过email获取用户信息
     *
     * @param email 用户电子邮箱
     * @return 爬取到的 ppt 的信息对象
     */
    No1PPT getNo1PptByEmail(String email);


    /**
     * 增加 ppt 的信息对象
     *
     * @param ppt 爬取到的 ppt 的信息对象
     */
    void addNo1PPT(No1PPT ppt);

    /**
     * 更新用户信息
     *
     * @param ppt 爬取到的 ppt 的信息对象
     * @return 返回值
     */
    int updateUser(No1PPT ppt);

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
