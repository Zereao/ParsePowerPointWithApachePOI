package com.parse.ppt.poi.controller.login;

import com.parse.ppt.poi.entity.User;
import com.parse.ppt.poi.service.login.UserLoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class UserLoginController {
    private static Logger logger = LogManager.getLogger(UserLoginController.class);

    @Autowired
    private UserLoginService userLoginService;

    @RequestMapping("/userLogin")
    @ResponseBody
    public String checkUserLogin(@RequestParam("account") String account,
                                 @RequestParam("password") String password,
                                 @RequestParam("rememberTag") String rememberTag) {
        logger.info("UserLoginController.checkUserLogin   ------->  start! " +
                "  account = " + account +
                "  password = " + password);

        String result = userLoginService.verifyUser(account, password);

        logger.info("UserLoginController.checkUserLogin   ------->  end! " +
                "  result = " + result);
        return "index";
    }

    @RequestMapping("/userRegister")
    @ResponseBody
    public String userRegister(@RequestParam("username") String username,
                               @RequestParam("email") String email,
                               @RequestParam("phoneNum") String phoneNum,
                               @RequestParam("password") String password) {
        logger.info("UserLoginController.userRegister   ------->  start! " +
                "  username = " + username +
                "  email = " + email +
                "  phoneNum = " + phoneNum +
                "  password = " + password);

        User user = new User(username, email, phoneNum, password);
        String result = userLoginService.registerUser(user);

        logger.info("UserLoginController.userRegister   ------->  end! " +
                "result = " + result);

        return "index";
    }
}
