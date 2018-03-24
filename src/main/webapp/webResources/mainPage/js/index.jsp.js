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

// 以下为自己写
// 页面初始化
function getInitializeInfo() {
    $.ajax({
        type: "post",
        url: "/onMainPageLoad/getMainPageLoadInfo",
        produces: "text/html;charset=UTF-8",
        async: false,
        error: function () {
            alert("获取主页初始化信息出现未知错误！");
        },
        success: function (data) {
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
var pageIndex = 0;
var task;

function getNo1PPTInfo() {
    var postInfo = {
        pageIndex: pageIndex
    };
    $.ajax({
        type: "post",
        url: "/no1ppt/loadNo1PPT",
        produces: "text/html;charset=UTF-8",
        data: postInfo,
        async: false,
        error: function () {
            alert("获取下载页PPT失败！");
        },
        success: function (data) {
            pageIndex += 40;
            data.forEach(function (currentValue, index, data) {
                var pptId = currentValue.id;
                var description = currentValue.description;
                var imgUrl = "/ZeroFilesOutput/NO1PPTS/" + pptId + "/" + pptId + ".png";
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
                        ppt2imgDisplay(pptId);
                    }, 3000);
                }, function () {
                    //这里去clear
                    clearTimeout(timer);//如果没停留3秒,直接会被clear掉,如果停留超过3秒,也一样会被clear,但是你要做的方法已经被执行了
                });
                theIdSelector.mouseleave(function () {
                    clearInterval(task);
                    var theImgId = theId + "_1";
                    $("#" + theImgId).attr("src", imgUrl);
                });
            });
        }
    });
}

function ppt2imgDisplay(thePptId) {
    var postInfo = {
        pptId: thePptId
    };
    var theImgId = "myID_2_" + thePptId + "_1";
    $("#" + theImgId).attr("src", "webResources/images/loading.gif");
    $.ajax({
        type: "post",
        url: "/no1ppt/ppt2img",
        produces: "text/html;charset=UTF-8",
        data: postInfo,
        error: function () {
            alert("访问ppt2img后台失败！");
        },
        success: function (data) {
            var imgArray = new Array(data);
            var imgIndex = 0;
            for (var i = 1; i <= data; i++) {
                imgArray[i] = "/ZeroFilesOutput/PPT2IMG/" + thePptId + "/" + i + ".png";
            }
            $(function () {
                task = setInterval(changeImg, 2000);
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