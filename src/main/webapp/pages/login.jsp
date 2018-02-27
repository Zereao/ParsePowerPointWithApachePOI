<%--
  Created by IntelliJ IDEA.
  User: Jupiter
  Date: 2018/2/23
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.parse.ppt.poi.entity.User" %>
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
        <div id="userLoginForm">
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
            <input type="text" id="reg_phoneNum" Name="phoneNum" placeholder="手机号码">
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


<script>

    <%-- 用户登录 按钮点击事件 --%>

    function userLogin() {
        var accountSelector = $("#login_account");
        var saveTag = $("#rememberTag").is(":checked");
        var userInfo = {
            account: accountSelector.val(),
            password: md5($("#login_password").val()),
            rememberTag: (saveTag)
        };
        if (userInfo.account.length === 0) {
            alert("请输入账户名！");
            accountSelector.css("background-color", "#292421");
        } else if (userInfo.account.indexOf("@") === 0) {
            alert("请输入正确的邮箱地址！");
            accountSelector.val("");
            accountSelector.css("background-color", "#292421");
        } else {
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
                    var result = data.toString();
                    if (result === "SUCCESS") {
                        alert("登录成功！");
                        //登录成功，返回首页
                        window.location.href = "../";
                    } else if (result === "WRONG_PASSWORD") {
                        alert("账户与密码不匹配！");
                    }
                }
            });
        }
    }

    <%-- 用户注册 按钮点击事件 --%>

    function registerUser() {
        var usernameSelector = $("#reg_username");
        var emailSelector = $("#reg_email");
        var phobeNumSelector = $("#reg_phoneNum");
        var regUserInfo = {
            username: usernameSelector.val(),
            email: emailSelector.val(),
            phoneNum: phobeNumSelector.val(),
            password: md5($("#reg_password").val())
        };
        if (regUserInfo.username.length === 0) {
            alert("请输入账户名！");
        } else if (regUserInfo.username.indexOf("@") === -1) {
            alert("请输入正确的账户名！不允许账户名中出现特殊字符 @");
            usernameSelector.val("");
            usernameSelector.css("background-color", "#292421");
        } else if (regUserInfo.email.length === 0 || regUserInfo.email.indexOf("@") === -1) {
            alert("请输入正确的邮箱地址！");
            emailSelector.val("");
            emailSelector.css("background-color", "#292421");
        } else if (regUserInfo.phoneNum.length === 0 || regUserInfo.phoneNum.length !== 11) {
            alert("请输入11位手机号码！");
            phobeNumSelector.css("background-color", "#292421");
            phobeNumSelector.val("");
        } else if (regUserInfo.password.length === 0) {
            alert("请输入用户密码！");
        } else {
            $.ajax({
                type: "post",
                url: "/login/userRegister",
                produces: "text/html;charset=UTF-8",
                data: regUserInfo,
                error: function (request) {
                    alert("向后端传递数据出现未知错误！");
                    window.location.href = "error.jsp";
                },
                success: function (data) {
                    var result = data.toString();
                    if (result === "SUCCESS") {
                        alert("❤注册成功❤");
                        //注册成功，返回首页
                        window.location.href = "../";
                    } else if (result === "ACCOUNT_ALREADY_EXISTS") {
                        // 该账户已经存在于数据库中，提示登录
                        alert("该账户已经存在！请直接登录！")
                    }
                }
            });
        }
    }

</script>

</body>
</html>
