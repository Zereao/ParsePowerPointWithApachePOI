//package com.parse.ppt.poi.service.common;
//
//import com.parse.ppt.poi.common.ReturnCode;
//import com.parse.ppt.poi.entity.User;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author Jupiter
// */
//@Service
//public class CookieServiceImplBackup implements CookieService {
//    private Logger logger = LogManager.getLogger(this.getClass());
//
//    private final UserService userService;
//
//    @Autowired
//    public CookieServiceImplBackup(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public String addUserCookie(User user, HttpServletResponse response) {
//        logger.info("------->  start! " +
//                "  user = " + user);
//        try {
//            String username = user.getUsername();
//            String email = user.getEmail();
//            String phoneNum = user.getPhoneNum();
//            String password = user.getPassword();
//            Cookie usernameCookie = new Cookie("username", username);
//            Cookie emailCookie = new Cookie("email", email);
//            Cookie phoneNumCookie = new Cookie("phoneNum", phoneNum);
//            Cookie passwordCookie = new Cookie("password", password);
//            List<Cookie> cookies = new ArrayList<>();
//            cookies.add(usernameCookie);
//            cookies.add(emailCookie);
//            cookies.add(phoneNumCookie);
//            cookies.add(passwordCookie);
//            for (Cookie cookie : cookies) {
//                // 单位 为 秒
//                cookie.setMaxAge(24 * 60 * 60);
//                //  下面这一行代码    【Super！！！！】  重要
//                cookie.setPath("/");
//                response.addCookie(cookie);
//            }
//            logger.info("------->  end!   SUCCESS!");
//            return ReturnCode.SUCCESS;
//        } catch (Exception e) {
//            logger.error("------->  ERROR!");
//            logger.error(e.getMessage());
//        }
//        return ReturnCode.FAILED;
//    }
//
//    @Override
//    public String removeUserCookie(HttpServletRequest request, HttpServletResponse response) {
//        logger.info("------->  start! ");
//        try {
//            Cookie[] cookies = request.getCookies();
//            if (cookies != null && cookies.length > 0) {
//                for (Cookie cookie : cookies) {
//                    boolean removeUsername = "username".equals(cookie.getName());
//                    boolean removeEmail = "email".equalsIgnoreCase(cookie.getName());
//                    boolean removePhoneNum = "phoneNum".equals(cookie.getName());
//                    boolean removePassword = "password".equals(cookie.getName());
//                    if (removeUsername || removeEmail || removePhoneNum || removePassword) {
//                        logger.info("cookie.getName() = " + cookie.getName()
//                                + "  cookie.getValue() = " + cookie.getValue());
//                        //  下面这一行代码    【Super！！！！】  重要
//                        cookie.setPath("/");
//                        //设置Cookie失效
//                        cookie.setMaxAge(0);
//                        //重新保存Cookie
//                        response.addCookie(cookie);
//                    }
//                }
//            }
//            logger.info("------->  end!   SUCCESS!");
//            return ReturnCode.SUCCESS;
//        } catch (Exception e) {
//            logger.error("------->  ERROR!");
//            logger.error(e.getMessage());
//        }
//        return ReturnCode.FAILED;
//    }
//
//    @Override
//    public User loadUserCookie(HttpServletRequest request) {
//        logger.info("------->  start! ");
//        try {
//            Cookie[] cookies = request.getCookies();
//            if (cookies != null && cookies.length > 0) {
//                logger.info(" cookies 数组不为null，尝试从cookies中获取用户信息。");
//                String username = "";
//                String email = "";
//                String phoneNum = "";
//                String password = "";
//                for (Cookie cookie : cookies) {
//                    if ("username".equals(cookie.getName())) {
//                        username = cookie.getValue();
//                    } else if ("email".equalsIgnoreCase(cookie.getName())) {
//                        email = cookie.getValue();
//                    } else if ("phoneNum".equals(cookie.getName())) {
//                        phoneNum = cookie.getValue();
//                    } else if ("password".equals(cookie.getName())) {
//                        password = cookie.getValue();
//                    }
//                }
//                boolean isRealCookie = !("".equals(username) || "".equals(email) || "".equals(phoneNum) || "".equals(password));
//                if (isRealCookie) {
//                    User newUser = userService.getUserByEmail(email);
//                    logger.info("从Cookie中获取到用户信息  the user of cookie = " + newUser);
//                    HttpSession session = request.getSession();
//                    session.setAttribute("user", newUser);
//                    logger.info("已将用户信息写入session     user = " + session.getAttribute("user"));
//                    logger.info("------->  end!  user = " + newUser);
//                    return newUser;
//                } else {
//                    logger.info("cookie中记录信息有误，返回 null 。" +
//                            "  username = " + username +
//                            "  email = " + email +
//                            "  phoneNum = " + phoneNum +
//                            "  password = " + password);
//                }
//            }
//        } catch (Exception e) {
//            logger.error("------->  ERROR!");
//            logger.error(e.getMessage());
//        }
//        return null;
//    }
//}
