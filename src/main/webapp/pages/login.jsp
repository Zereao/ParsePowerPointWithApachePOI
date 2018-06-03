<%--
  Created by IntelliJ IDEA.
  User: Jupiter
  Date: 2018/2/23
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎登陆/注册❤当一个人不能拥有的时候，他唯一能做的便是不要忘记。</title>
    <link rel="shortcut icon" type="image/x-icon" href="../webResources/favicon/sun.ico" media="screen"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <script src="../webResources/js/jquery-3.2.1.min.js"></script>
    <script src="../webResources/js/jsencrypt.min.js"></script>
    <%-- 引入自己定义的一些JS --%>
    <script src="../webResources/my-js/login.jsp.js"></script>

    <link rel="stylesheet" href="../webResources/css/login_style.css" type="text/css" media="all">

</head>
<body onload="onLoginPageLoad()">
<h1>欢迎登陆/注册</h1>
<div class="container w3layouts agileits">
    <div class="login w3layouts agileits">
        <h2>登 录</h2>
        <div>
            <input type="text" id="login_account" Name="account" placeholder="邮箱/手机号">
            <input type="password" id="login_password" Name="password" placeholder="密码">
        </div>
        <ul class="tick w3layouts agileits">
            <li>
                <input type="checkbox" id="rememberTag" checked="checked">
                <label for="rememberTag"><span></span>一天内记住我</label>
            </li>
        </ul>
        <div class="send-button w3layouts agileits">
            <div>
                <input type="submit" onclick="userLogin()" value="登&emsp;录">
            </div>
        </div>
        <div class="social-icons w3layouts agileits">
            <p>- 其他方式登录 -</p>
            <ul>
                <li class="qq"><a href="#">
                    <span class="icons w3layouts agileits"></span>
                    <span class="text w3layouts agileits">QQ</span></a></li>
                <li class="weixin w3ls"><a href="#">
                    <span class="icons w3layouts"></span>
                    <span class="text w3layouts agileits">微信</span></a></li>
                <li class="weibo aits"><a href="#">
                    <span class="icons agileits"></span>
                    <span class="text w3layouts agileits">微博</span></a></li>
            </ul>
        </div>
    </div>
    <div class="register w3layouts agileits">
        <h2>注 册</h2>
        <div id="userRegsiterForm">
            <input type="text" id="reg_username" Name="username" placeholder="用户名">
            <input type="text" id="reg_email" Name="email" placeholder="邮箱">
            <input type="text" id="reg_phoneNum" Name="mobile" placeholder="手机号码">
            <input type="password" id="reg_password" Name="password" placeholder="密码">
        </div>
        <div class="send-button w3layouts agileits">
            <div>
                <input type="submit" onclick="registerUser()" value="注&emsp;册">
            </div>
        </div>
        <div class="clear"></div>
    </div>

    <div class="clear"></div>

</div>

<div class="footer">
    <p style="font-size: 1.2rem">Copyright &copy; 2017-2018
        <a href="https://github.com/Zereao" target="_blank" title="访问我的GitHub"> 白露</a>
        All Rights Reserved</p>
</div>

</body>
</html>
