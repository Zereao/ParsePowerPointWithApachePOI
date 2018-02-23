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
    <title>当一个人不能拥有的时候，他唯一能做的便是不要忘记。</title>
    <!--网站图标-->
    <link rel="shortcut icon" type="image/x-icon" href="../../webResources/favicon/sun.ico" media="screen"  />

    <!-- Meta-Tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!-- //Meta-Tags -->

    <script src="../../webResources/js/jquery-3.2.1.min.js"></script>         <!-- jQuery (https://jquery.com/download/) -->
    <script type="application/x-javascript">
        addEventListener("load", function() {
            setTimeout(hideURLbar, 0);
        }, false);
        function hideURLbar(){
            window.scrollTo(0,1);
        }
    </script>

    <!-- Style -->
    <link rel="stylesheet" href="../../webResources/css/loginPageStyle.css" type="text/css" media="all">
    <%--<link rel="stylesheet" href="../resources/mainPage/css/templatemo-style.css">--%>
    <!-- Templatemo style -->
</head>
<body>

<h1>欢迎登陆</h1>
<div class="container w3layouts agileits">
    <div class="login w3layouts agileits">
        <h2>登 录</h2>
        <div>
            <input type="text" id="login_account" Name="account" placeholder="邮箱/手机号" required="">
            <input type="password" id="login_password" Name="password" placeholder="密码" required="">
        </div>
        <ul class="tick w3layouts agileits">
            <li>
                <input type="checkbox" id="brand1" value="">
                <label for="brand1"><span></span>记住我</label>
            </li>
        </ul>
        <div class="send-button w3layouts agileits">
            <div>
                <input type="submit" onclick="getLoginUserInfo()" value="登&emsp;录">
            </div>
        </div>
        <!--<a href="#">记住密码?</a>-->
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
    <!--<div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >企业网站模板</a></div>-->
    <div class="register w3layouts agileits">
        <h2>注 册</h2>
        <!--<form action="#" method="post">-->
        <div>
            <input type="text" id="reg_account" Name="user_name" placeholder="用户名" required="">
            <input type="text" id="reg_e_mail" Name="e_mail" placeholder="邮箱" required="">
            <input type="password" id="reg_password" Name="password" placeholder="密码" required="">
            <input type="text" id="reg_phone_num" Name="phone_num" placeholder="手机号码" required="">
        </div>
        <!--</form>-->
        <div class="send-button w3layouts agileits">
            <!--<form>-->
            <div>
                <input type="submit" onclick="addRegUserInfo()" value="注&emsp;册">
            </div>
            <!--</form>-->
        </div>
        <div class="clear"></div>
    </div>

    <div class="clear"></div>

</div>

<%--<div class="footer w3layouts agileits ">--%>
    <%--<p class="tm-copyright-text" style="font-size: 1.2rem">Copyright &copy; 2016-2017--%>
        <%--<a href="https://github.com/Zereao" target="_blank" title="访问我的GitHub"> 白露</a>--%>
        <%--All Rights Reserved</p>--%>
<%--</div>--%>


<script>
    function addRegUserInfo() {
        var regUserInfo = {
            user_name: $("#reg_account").val(),
            e_mail: $("#reg_e_mail").val(),
            password: $("#reg_password").val(),
            phone_num:$("#reg_phone_num").val()
        };
        $.ajax({
            type: "post",
            url:"/loginPage/servlet/RegUserInfoServlet",
            produces:"text/html;charset=UTF-8",
            data:regUserInfo,
            error: function(request) {
                alert("连接错误");
            },
            success: function(data) {
                alert("❤注册成功！❤")
            }
        });
    }

    function getLoginUserInfo() {
        var regUserInfo = {
            account: $("#login_account").val(),
            password: $("#login_password").val()
        };
        $.ajax({
            type: "post",
            url:"/loginPage/servlet/LoginUserInfoServlet",
            produces:"text/html;charset=UTF-8",
            data:regUserInfo,
            error: function(request) {
                alert("连接错误");
            },
            success: function(data) {
                alert(data)
            }
        });
    }
</script>


</body>
</html>
