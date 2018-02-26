<%@ page import="com.parse.ppt.poi.entity.User" %><%--
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
    <script src="../webResources/js/md5.min.js"></script>

    <link rel="stylesheet" href="../webResources/css/login_style.css" type="text/css" media="all">

    <script type="application/x-javascript">
        <%--因为chrome等浏览器会有滚动缓存功能，比如你在A页面滚动后跳转到B页面，
             点击返回键回到A页面，会发现滚动条位置仍然保持。
             所以每次页面加载，都将页面滚动到 (0,1) 位置 --%>
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>

    <%
        User user = (User) session.getAttribute("user");
        // 用户已经登录在线，session中存在用户信息，则返回主页
        boolean isOnline = user != null && !("".equals(user.getUsername()));
        if (isOnline) {
            response.sendRedirect("../");
        }
    %>
</head>
<body>

<h1>欢迎登陆/注册</h1>
<div class="container w3layouts agileits">
    <div class="login w3layouts agileits">
        <h2>登 录</h2>
        <div>
            <input type="text" id="login_account" Name="account" placeholder="邮箱/手机号" required="required">
            <input type="password" id="login_password" Name="password" placeholder="密码" required="required">
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
                <%--<div class="clear"> </div>--%>
            </ul>
        </div>
        <div class="clear"></div>
    </div>
    <div class="register w3layouts agileits">
        <h2>注 册</h2>
        <!--<form action="#" method="post">-->
        <div>
            <input type="text" id="reg_username" Name="username" placeholder="用户名" required="required">
            <input type="text" id="reg_e_mail" Name="e_mail" placeholder="邮箱" required="required">
            <input type="password" id="reg_password" Name="password" placeholder="密码" required="required">
            <input type="text" id="reg_phoneNum" Name="phone_num" placeholder="手机号码" required="required">
        </div>
        <!--</form>-->
        <div class="send-button w3layouts agileits">
            <!--<form>-->
            <div>
                <input type="submit" onclick="registerUser()" value="注&emsp;册">
            </div>
            <!--</form>-->
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
<%-- 在下面这个标签中 写java方法 --%>
<%--<%!--%>
<%--public String addUserCookie(HttpServletRequest request, HttpServletResponse response) {--%>
<%--//首先判断用户是否选择了记住登陆状态--%>

<%--String[] isUseCookie = request.getParameterValues("isUseCookie");--%>
<%--if (isUseCookie != null && isUseCookie.length > 0) {--%>
<%--//把用户名和密码保存在Cookie对象里面--%>
<%--String username = request.getParameter("username");--%>
<%--String password = request.getParameter("password");--%>
<%--Cookie usernameCookie = new Cookie("username", username);--%>
<%--Cookie passwordCookie = new Cookie("password", password);--%>
<%--usernameCookie.setMaxAge(86400);--%>
<%--passwordCookie.setMaxAge(86400);--%>
<%--response.addCookie(usernameCookie);--%>
<%--response.addCookie(passwordCookie);--%>
<%--} else {--%>
<%--//已保存Cookie设置失效--%>
<%--Cookie[] cookies = request.getCookies();--%>
<%--if (cookies != null && cookies.length > 0) {--%>
<%--for (Cookie c : cookies) {--%>
<%--if (c.getName().equalsIgnoreCase("username") || c.getName().equalsIgnoreCase("password")) {--%>
<%--c.setMaxAge(0); //设置Cookie失效--%>
<%--response.addCookie(c); //重新保存Cookie--%>
<%--}--%>
<%--}--%>
<%--}--%>
<%--}--%>
<%--}--%>

<%--%>--%>

<script>
    <%-- 用户登录 按钮点击事件 --%>

    function userLogin() {
        var saveTag = $("#rememberTag").is(":checked");
        // alert(svavTag);
        var userInfo = {
            account: $("#login_account").val(),
            password: md5($("#login_password").val()),
            rememberTag: (saveTag)
        };
        $.ajax({
            type: "post",
            url: "/login/userLogin",
            produces: "text/html;charset=UTF-8",
            data: userInfo,
            error: function (request) {
                alert("网络连接错误！");
                window.location.href = "error.jsp";
            },
            success: function (data) {
                if (data.toString() === "SUCCESS") {
                    alert("登录成功！");
                    //登录成功，返回首页
                    window.location.href = "../";
                }

                // window.location.href = "../index.jsp"
            }
        });
    }


    <%-- 用户注册 按钮点击事件 --%>

    function registerUser() {
        var regUserInfo = {
            username: $("#reg_username").val(),
            email: $("#reg_e_mail").val(),
            phoneNum: $("#reg_phoneNum").val(),
            password: md5($("#reg_password").val())
        };
        $.ajax({
            type: "post",
            url: "/login/userRegister",
            produces: "text/html;charset=UTF-8",
            data: regUserInfo,
            error: function (request) {
                alert("网络连接错误");
                window.location.href = "error.jsp";
            },
            success: function (data) {
                if (data.toString() === "SUCCESS") {
                    alert("❤注册成功！❤！");
                    //注册成功，返回首页
                    window.location.href = "../";
                }

            }
        });
    }


</script>


</body>
</html>
