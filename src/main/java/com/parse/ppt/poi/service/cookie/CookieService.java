package com.parse.ppt.poi.service.cookie;

import com.parse.ppt.poi.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author Jupiter
 * 当用户登录时选则了 一天内记住我 时，添加本地Cookie
 */
@Service
public interface CookieService {

    /**
     * 添加用户Cookie信息
     *
     * @param user 用户对象，将其的部分属性保存Cookie
     * @return 返回码
     */
    String addUserCookie(User user);
}
