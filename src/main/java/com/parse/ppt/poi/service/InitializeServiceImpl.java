package com.parse.ppt.poi.service;

import com.parse.ppt.poi.dao.RedisCacheDao;
import com.parse.ppt.poi.entity.User;
import com.parse.ppt.poi.service.common.CookieService;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Jupiter
 * @date 2018/03/09/10:45
 */
@Service
public class InitializeServiceImpl implements InitializeService {
    private Logger logger = LogManager.getLogger(this.getClass());

    private final CookieService cookieService;
    private final RedisCacheDao redisCacheDao;

    @Autowired
    public InitializeServiceImpl(CookieService cookieService, RedisCacheDao redisCacheDao) {
        this.cookieService = cookieService;
        this.redisCacheDao = redisCacheDao;
    }

    @Override
    public JSONObject getInitializeInfo(HttpServletRequest request) {
        logger.info("------->  start! ");
        try {
            HttpSession session = request.getSession();
            // 用户未登录时，默认的信息
            JSONObject obj = new JSONObject();
            obj.put("welcomeWord", "Hi,Melody");
            obj.put("welcomeTitle", "点我登录/注册");
            obj.put("isHidden", "true");

            // 默认的首页文章
            obj.put("essayTitle", redisCacheDao.get("essayTitle"));
            obj.put("essayContent", redisCacheDao.get("essayContent").replace("\n", "<br/>"));

            User user = null;
            if (session.getAttribute("user") == null) {
                user = cookieService.loadUserCookie(request);
            } else {
                user = (User) session.getAttribute("user");
            }
            logger.info("------->    user = " + user);
            if (user != null) {
                session.setAttribute("user", user);
                obj.put("username", user.getUsername());
                obj.put("email", user.getEmail());
                obj.put("phoneNum", user.getPhoneNum());

                obj.put("welcomeWord", "Hi," + user.getUsername());
                obj.put("welcomeTitle", "欢迎回来，亲爱的" + user.getUsername() + "。右键点击退出登录");
                obj.put("isHidden", "false");
                // 读取用户设置的首页文章
                String essayTitle = user.getMainPageEssayTitle();
                String essayContent = user.getMainPageEssayContent();
                if (essayTitle != null) {
                    obj.put("essayTitle", essayTitle);
                    obj.put("essayContent", essayContent);
                }
            }
            logger.info("------->  end!");
            return obj;
        } catch (Exception e) {
            logger.error("------->  ERROR!    返回null");
            logger.error(e.getMessage());
        }
        return null;
    }
}
