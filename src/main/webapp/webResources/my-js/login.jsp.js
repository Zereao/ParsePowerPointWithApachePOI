/*因为chrome等浏览器会有滚动缓存功能，比如你在A页面滚动后跳转到B页面，
             点击返回键回到A页面，会发现滚动条位置仍然保持。
             所以每次页面加载，都将页面滚动到 (0,1) 位置 */
addEventListener("load", function () {
    setTimeout(hideURLbar, 0);
}, false);

function hideURLbar() {
    window.scrollTo(0, 1);
}

function onLoginPageLoad() {
    var userLoginStatus = 0;
    $.ajax({
        type: "get",
        url: "/login/getUserLoginStatus",
        produces: "text/html;charset=UTF-8",
        async: false,
        error: function () {
            alert("获取用户登陆状态出错！");
        },
        success: function (data) {
            userLoginStatus = data;
        }
    });
    // 如果用户已经登录，直接跳转到首页
    if (userLoginStatus === 1) {
        window.location.href = "index.jsp";
    }
}

// 加密信息
function encryptInfo(date) {
    var publicKey;
    $.ajax({
        type: "post",
        url: "/login/getPublicKey",
        async: false,
        produces: "text/html;charset=UTF-8",
        error: function (request) {
            alert("从后端获取加密公钥错误！");
        },
        success: function (data) {
            publicKey = data;
        }
    });
    var jsencrypter = new JSEncrypt();
    jsencrypter.setPublicKey(publicKey);
    return jsencrypter.encrypt(date);
}

// // 用户登录 按钮点击事件
function userLogin() {
    var accountSelector = $("#login_account");
    var saveTag = $("#rememberTag").is(":checked");
    var userInfo = {
        account: accountSelector.val(),
        password: encryptInfo($("#login_password").val()),
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
            contentType: "application/json",
            data: JSON.stringify(userInfo),
            error: function (request) {
                alert("网络连接错误！");
            },
            success: function (data) {
                var result = data.toString();
                if (result === "SUCCESS") {
                    alert("登录成功！");
                    //登录成功，返回首页
                    window.location.href = "../";
                } else if (result === "WRONG_PASSWORD") {
                    alert("账户与密码不匹配！");
                } else if (result === "FAILED") {
                    alert("登陆成功！但后端Cookie添加失败！");
                    window.location.href = "../";
                } else if (result === "ACCOUNT_NOT_EXISTS") {
                    alert("账户不存在！");
                }
            }
        });
    }
}

//用户注册 按钮 点击事件
function registerUser() {
    var usernameSelector = $("#reg_username");
    var emailSelector = $("#reg_email");
    var phoneNumSelector = $("#reg_phoneNum");
    var regUserInfo = {
        username: usernameSelector.val(),
        email: emailSelector.val(),
        mobile: phoneNumSelector.val(),
        password: encryptInfo($("#reg_password").val())
    };
    if (regUserInfo.username.length === 0) {
        alert("请输入账户名！");
    } else if (regUserInfo.username.indexOf("@") !== -1) {
        alert("请输入正确的账户名！不允许账户名中出现特殊字符 @");
        usernameSelector.val("");
        usernameSelector.css("background-color", "#292421");
    } else if (regUserInfo.email.length === 0 || regUserInfo.email.indexOf("@") === -1) {
        alert("请输入正确的邮箱地址！");
        emailSelector.val("");
        emailSelector.css("background-color", "#292421");
    } else if (regUserInfo.mobile.length === 0 || regUserInfo.mobile.length !== 11) {
        alert("请输入11位手机号码！");
        phoneNumSelector.css("background-color", "#292421");
        phoneNumSelector.val("");
    } else if (regUserInfo.password.length === 0) {
        alert("请输入用户密码！");
    } else {
        $.ajax({
            type: "post",
            url: "/login/userRegister",
            produces: "text/html;charset=UTF-8",
            contentType: "application/json",
            data: JSON.stringify(regUserInfo),
            error: function (request) {
                alert("向后端传递数据出现未知错误！");
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
                } else if (result === "FAILED") {
                    alert("接收到返回值为 FAILED ，请联系运维确定出错信息！")
                }
            }
        });
    }
}