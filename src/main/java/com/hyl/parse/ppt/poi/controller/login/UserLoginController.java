package com.hyl.parse.ppt.poi.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class UserLoginController {


    @RequestMapping("/userLogin")
    @ResponseBody
    public String checkUserLogin(@RequestParam("account") String account,
                                 @RequestParam("password") String password,
                                 @RequestParam("rememberTag") String rememberTag) {
        System.out.println("account = " + account);
        System.out.println("password = " + password);
        System.out.println("rememberTad = " + rememberTag);
        return "login";
    }
}
