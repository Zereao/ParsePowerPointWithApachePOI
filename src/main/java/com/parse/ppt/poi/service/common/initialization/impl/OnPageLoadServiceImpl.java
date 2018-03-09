package com.parse.ppt.poi.service.common.initialization.impl;

import com.parse.ppt.poi.entity.User;
import com.parse.ppt.poi.service.common.initialization.OnPageLoadService;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author Jupiter
 * @date 2018/03/09/10:45
 */
@Service
public class OnPageLoadServiceImpl implements OnPageLoadService {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public JSONObject getInitializeInfo(HttpSession session) {
        try {
            logger.info("OnPageLoadServiceImpl.getInitializeInfo()   ------->  start!");
            JSONObject obj = new JSONObject();
            obj.put("welcomeWord", "Hi,Melody");
            obj.put("welcomeTitle", "点我登录/注册");
            obj.put("isHidden", "true");
            if (session.getAttribute("user") != null) {
                // session 中存在 user 属性，说明用户已经登录
                User user = (User) session.getAttribute("user");
                obj.put("username", user.getUsername());
                obj.put("email", user.getEmail());
                obj.put("phoneNum", user.getPhoneNum());

                obj.put("welcomeWord", "Hi," + user.getUsername());
                obj.put("welcomeTitle", "欢迎回来，亲爱的" + user.getUsername() + "。右键点击退出登录");
                obj.put("isHidden", "false");
                logger.info("OnPageLoadServiceImpl.getInitializeInfo()   ------->  end!   JSONObject = " + obj);
                return obj;
            }
            logger.info("OnPageLoadServiceImpl.getInitializeInfo()   ------->  end!   JSONObject = " + obj);
            return obj;
        } catch (Exception e) {
            logger.error("OnPageLoadServiceImpl.getInitializeInfo()   ------->  ERROR!  返回 null ");
            logger.error(e.getMessage());
        }
        return null;
    }
}
