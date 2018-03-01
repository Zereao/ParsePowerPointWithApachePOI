package com.parse.ppt.poi.commom;

/**
 * @author Jupiter
 * 公共返回码 类
 */
public class ReturnCode {
    /**
     * 该段代码执行成功，返回
     */
    public static final String SUCCESS = "SUCCESS";


    /**
     * 该段逻辑执行出现错误，返回
     */
    public static final String FAILED = "FAILED";

    /**
     * login.jsp页面，密码验证失败 ———— 密码错误
     */
    public static final String WRONG_PASSWORD = "WRONG_PASSWORD";

    /**
     * 用户注册，用户账号已经存在
     */
    public static final String ACCOUNT_ALREADY_EXISTS = "ACCOUNT_ALREADY_EXISTS";

    /**
     * 邮件发送失败
     */
    public static final String MAIL_SEND_FAILED = "MAIL_SEND_FAILED";

    /**
     * Redis .remove(String key)  ;  key不存在
     */
    public static final String KEY_NOT_EXISTS = "KEY_NOT_EXISTS";
}
