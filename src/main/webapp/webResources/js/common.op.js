// 全局变量，用于前端判断用户是否在线  0 - 离线 ； 1 - 在线
var userLoginStatus = 0;

function getUserLoginStatus() {
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
}