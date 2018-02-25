package com.parse.ppt.poi.controller.login;

import com.parse.ppt.poi.service.login.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;


    @RequestMapping("/userLogin")
    @ResponseBody
    public String checkUserLogin(@RequestParam("account") String account,
                                 @RequestParam("password") String password,
                                 @RequestParam("rememberTag") String rememberTag) {
        String result = userLoginService.verifyUser(account, password);
        return "index";
    }
    
}
