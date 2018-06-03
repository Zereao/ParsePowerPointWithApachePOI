// 页面适配
$(window).load(function () {
    adjustHeightOfPage(1);
    $('.gallery-one').magnificPopup({
        delegate: 'a',
        type: 'image',
        gallery: {enabled: true}
    });
    $('.gallery-two').magnificPopup({
        delegate: 'a',
        type: 'image',
        gallery: {enabled: true}
    });
    $('#tmNavbar').find('a').click(function () {
        $('#tmNavbar').collapse('hide');
        adjustHeightOfPage($(this).data("no"));
    });
    $(window).resize(function () {
        var currentPageNo = $(".cd-hero-slider li.selected .js-tm-page-content").data("page-no");
        setTimeout(function () {
            adjustHeightOfPage(currentPageNo);
        }, 1000);
    });
    $('body').addClass('loaded');
});

function adjustHeightOfPage(pageNo) {
    var offset = 80;
    var pageContentHeight = $(".cd-hero-slider li:nth-of-type(" + pageNo + ") .js-tm-page-content").height();
    if ($(window).width() >= 992) {
        offset = 120;
    }
    else if ($(window).width() < 480) {
        offset = 40;
    }
    var totalPageHeight = 15 + $('.cd-slider-nav').height()
        + pageContentHeight + offset
        + $('.tm-footer').height();
    if (totalPageHeight > $(window).height()) {
        $('.cd-hero-slider').addClass('small-screen');
        $('.cd-hero-slider li:nth-of-type(' + pageNo + ')').css("min-height", totalPageHeight + "px");
    }
    else {
        $('.cd-hero-slider').removeClass('small-screen');
        $('.cd-hero-slider li:nth-of-type(' + pageNo + ')').css("min-height", "100%");
    }
}

/**
 * 以下为自己写
 */

// index.jsp页面初始化
function onMainPageLoad() {
    var userLoginStatus = getInitializeInfo();
    // 监听 id 为 login 的控件的鼠标左右键点击事件
    $('#login').mousedown(function (e) {
        if (e.which === 1) {
            // alert('这是左键单击事件');
            if (userLoginStatus === 0) {
                window.location.href = "../../pages/login.jsp";
            }
        } else if (e.which === 3) {
            if (userLoginStatus === 1) {
                userLogout();
            }
        }
    });
    // 监听滚动条是否下拉到最下面
    $("#pptGallery").scroll(function () {
        var nDivHight = $("#pptGallery").height();
        var nScrollHight = $(this)[0].scrollHeight;
        var nScrollTop = $(this)[0].scrollTop;
        if ((nScrollTop + nDivHight) / nScrollHight >= 0.98) {
            getNo1PPT();
        }
    });
}

function getInitializeInfo() {
    var userLoginStatus = 0;
    $.ajax({
        type: "get",
        url: "/initialize/getInitializeInfo",
        produces: "text/html;charset=UTF-8",
        async: false,
        error: function () {
            alert("获取主页初始化信息出现未知错误！");
        },
        success: function (data) {
            userLoginStatus = data.userLoginStatus;
            var loginControlSelector = $("#login");
            var id_1_Selector = $("#myID_0_1");
            loginControlSelector.attr("title", data.welcomeTitle);
            loginControlSelector.append('<i class="fa fa-send-o tm-brand-icon"></i>' + data.welcomeWord);
            if (data.isHidden === "true") {
                id_1_Selector.hide();
            } else {
                $("#myID_0_2").text(data.username);
                id_1_Selector.show();
            }
            var myID_1_1_Selector = $("#myID_1_1");
            var myID_1_2_Selector = $("#myID_1_2");
            myID_1_1_Selector.text(data.essayTitle);
            var tabInstead = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            myID_1_2_Selector.html(tabInstead + data.essayContent);
        }
    });
    return userLoginStatus;
}

var isFirstClick_no1ppt = 0;
var isFirstClick_poippt = 0;

/**
 *根据tag执行对应的getPPT方法
 * @param tag 2-No1PPT下载方法<p>3-PoiPPT下载方法
 */
function getPPT(tag) {
    if (tag === 2 && isFirstClick_no1ppt === 0) {
        getNo1PPT();
        isFirstClick_no1ppt++;
    } else if (tag === 3 && isFirstClick_poippt === 0) {
        // getPoiPPT();
        isFirstClick_poippt++;
    }
}

// 用户登陆登出、设置首页文章
function userLogout() {
    var isLogout = confirm("确定注销当前用户？");
    if (isLogout === true) {
        $.ajax({
            type: "post",
            url: "/login/userLogout",
            produces: "text/html;charset=UTF-8",
            error: function () {
                alert("访问后端出现未知错误！");
            },
            success: function () {
                alert("❤注销成功，期待再会❤");
                location.reload();
            }
        });
    }
}

function setMainPageEssay() {
    var essayInfo = {
        essayTitle: $("#essay_title").val(),
        essayContent: $("#essay_content").val()
    };
    $.ajax({
        type: "post",
        url: "/admin/setEssay",
        data: essayInfo,
        produces: "text/html;charset=UTF-8",
        error: function () {
            alert("访问后端出现未知错误！");
        },
        success: function (data) {
            if (data !== null) {
                var myID_1_1_Selector = $("#myID_1_1");
                var myID_1_2_Selector = $("#myID_1_2");
                myID_1_1_Selector.text(data.essayTitle);
                myID_1_2_Selector.text(data.essayContent);
            }
        }
    });
}

// No1PPT 下载页面相关
// 一个全局JS变量，用来表示下载页已经下载的页数。JS变量，一刷新，就相当于重新赋值，然后归零
var no1pptPageIndex = 0;
var no1pptTask;

function getNo1PPT() {
    var jsonData = {
        pageIndex: no1pptPageIndex
    };
    $.ajax({
        type: "get",
        url: "/no1ppt/loadNo1PPT",
        produces: "text/html;charset=UTF-8",
        data: jsonData,
        async: false,
        error: function () {
            alert("获取下载页PPT失败！");
        },
        success: function (data) {
            no1pptPageIndex += 40;
            data.forEach(function (currentValue, index, data) {
                var pptId = currentValue.id;
                var description = currentValue.description;
                var imgUrl = "/ZeroFilesOutput/ppts/no1ppts/" + pptId + "/" + pptId + ".png";
                var pptName = currentValue.pptName;
                //          myID_第二页_pptId
                var theId = "myID_2_" + pptId;
                var htmlText = '<div id="' + theId + '" class="grid-item" title="' + description + '">' +
                    ' <a href="/no1ppt/downloadNo1PPT?id=' + pptId + '" target="_blank" download="' + pptName + '">' +
                    '   <img id="' + theId + '_1' + '" src="' + imgUrl + '" alt="Image" class="img-fluid tm-img" style="height: 200px">' +
                    ' </a>' +
                    ' </div>';
                $("#pptGallery").append(htmlText);
                var theIdSelector = $("#" + theId);
                var timer;
                theIdSelector.hover(function () {
                    timer = setTimeout(function () {
                        no1ppt2imgDisplay(pptId);
                    }, 3000);
                }, function () {
                    //这里去clear
                    clearTimeout(timer);//如果没停留3秒,直接会被clear掉,如果停留超过3秒,也一样会被clear,但是你要做的方法已经被执行了
                });
                theIdSelector.mouseleave(function () {
                    clearInterval(no1pptTask);
                    var theImgId = theId + "_1";
                    $("#" + theImgId).attr("src", imgUrl);
                });
            });
        }
    });
}

function no1ppt2imgDisplay(thePptId) {
    var jsonData = {
        pptId: thePptId
    };
    var theImgId = "myID_2_" + thePptId + "_1";
    $("#" + theImgId).attr("src", "webResources/images/loading.gif");
    $.ajax({
        type: "get",
        url: "/no1ppt/ppt2img",
        produces: "text/html;charset=UTF-8",
        data: jsonData,
        error: function () {
            alert("访问ppt2img后台失败！");
        },
        success: function (data) {
            var imgArray = new Array(data);
            var imgIndex = 0;
            for (var i = 1; i <= data; i++) {
                imgArray[i] = "/ZeroFilesOutput/ppt2imgs/no1ppt2imgs/" + thePptId + "/" + i + ".png";
            }
            $(function () {
                no1pptTask = setInterval(changeImg, 2000);
            });

            function changeImg() {
                $("#" + theImgId).attr("src", imgArray[imgIndex]);
                if (imgIndex < imgArray.length) {
                    imgIndex++;
                } else {
                    imgIndex = 0;
                }
            }
        }
    });
}

// POI处理部分

var poiTask;

function onPoiSerach() {
    var jsonData = {
        keywords: $("#myID_3_1").val()
    };
    $.ajax({
        type: "post",
        url: "/poi/search",
        produces: "text/html;charset=UTF-8",
        data: jsonData,
        error: function () {
            alert("Error-3-1:搜索失败！");
        },
        success: function (data) {
            // noinspection JSUnusedLocalSymbols
            data.forEach(function (currentValue, index, data) {
                var pptId = currentValue.id;
                var description = currentValue.description;
                var imgUrl = "/ZeroFilesOutput/ppts/no1ppts/" + pptId + "/" + pptId + ".png";
                var pptName = currentValue.pptName;
                //          myID_第二页_pptId
                var theId = "myID_3_" + pptId;
                var htmlText = '<div id="' + theId + '" class="grid-item" title="' + description + '">' +
                    ' <a href="/poi/downloadPoiPPT?id=' + pptId + '" target="_blank" download="' + pptName + '">' +
                    '   <img id="' + theId + '_1' + '" src="' + imgUrl + '" alt="Image" class="img-fluid tm-img" style="height: 200px">' +
                    ' </a>' +
                    ' </div>';
                $("#poiGallery").append(htmlText);
                var theIdSelector = $("#" + theId);
                var timer;
                theIdSelector.hover(function () {
                    timer = setTimeout(function () {
                        operatePoiPPT(pptId);
                    }, 3000);
                }, function () {
                    //这里去clear
                    clearTimeout(timer);//如果没停留3秒,直接会被clear掉,如果停留超过3秒,也一样会被clear,但是你要做的方法已经被执行了
                });
                theIdSelector.mouseleave(function () {
                    clearInterval(poiTask);
                    var theImgId = theId + "_1";
                    $("#" + theImgId).attr("src", imgUrl);
                });
            });
        }
    });
}

function operatePoiPPT(thePptId) {
    var jsonData = {
        pptId: thePptId
    };
    var theImgId = "myID_3_" + thePptId + "_1";
    $("#" + theImgId).attr("src", "webResources/images/loading.gif");
    $.ajax({
        type: "get",
        url: "/poi/operatePoiPPT",
        produces: "text/html;charset=UTF-8",
        data: jsonData,
        async: false,
        error: function () {
            alert("访问ppt2img后台失败！");
        },
        success: function (data) {
            var imgArray = new Array(data);
            var imgIndex = 0;
            for (var i = 1; i <= data; i++) {
                imgArray[i] = "/ZeroFilesOutput/ppt2imgs/PoiPPTS/Rebuild/" + thePptId + "/" + i + ".png";
            }
            $(function () {
                no1pptTask = setInterval(changeImg, 2000);
            });

            function changeImg() {
                $("#" + theImgId).attr("src", imgArray[imgIndex]);
                if (imgIndex < imgArray.length) {
                    imgIndex++;
                } else {
                    imgIndex = 0;
                }
            }
        }
    });
}
