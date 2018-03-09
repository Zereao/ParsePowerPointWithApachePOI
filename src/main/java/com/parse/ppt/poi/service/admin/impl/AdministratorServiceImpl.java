package com.parse.ppt.poi.service.admin.impl;

import com.parse.ppt.poi.entity.User;
import com.parse.ppt.poi.service.admin.AdministratorService;
import com.parse.ppt.poi.service.common.user.UserService;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author Jupiter
 * @date 2018/03/09/14:05
 */
@Service
public class AdministratorServiceImpl implements AdministratorService {
    private Logger logger = LogManager.getLogger(this.getClass());

    private final UserService userService;

    @Autowired
    public AdministratorServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public JSONObject setMainPageEssay(String essayTitle, String essayContent, HttpSession session) {
        logger.info("------->  start! " +
                "   essayTitle = " + essayTitle +
                "   essayContent = " + essayContent);
        try {
            User user = (User) session.getAttribute("user");
            String result = userService.updateUserEssay(user.getEmail(), essayTitle, essayContent);
            JSONObject essayInfoJson = new JSONObject();
            essayInfoJson.put("essayTitle", essayTitle);
            essayInfoJson.put("essayContent", essayContent);
            logger.info("------->  end! " +
                    "  essayInfoJson = " + essayInfoJson);
            return essayInfoJson;
        } catch (Exception e) {
            logger.error("------->  ERROR! ");
            logger.error(e.getMessage());
        }
        return null;
    }
}
