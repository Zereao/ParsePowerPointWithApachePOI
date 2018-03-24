<%--
  Created by IntelliJ IDEA.
  User: Jupiter
  Date: 2018/2/23
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!--网站图标-->
    <link rel="shortcut  icon" type="image/x-icon" href="webResources/favicon/sun.ico" media="screen"/>
    <!--网站名称-->
    <title>故事长满天涯海角，包括你和你的故乡。</title>

    <script src="webResources/mainPage/js/html5shiv.min.js"></script>
    <script src="webResources/mainPage/js/respond.min.js"></script>
    <script src="webResources/mainPage/js/jquery-1.11.3.min.js"></script>
    <script src="webResources/mainPage/js/tether.min.js"></script>
    <script src="webResources/mainPage/js/bootstrap.min.js"></script>
    <script src="webResources/mainPage/js/hero-slider-main.js"></script>
    <script src="webResources/mainPage/js/masonry.pkgd.min.js"></script>
    <script src="webResources/mainPage/js/jquery.magnific-popup.min.js"></script>

    <link rel="stylesheet" href="webResources/mainPage/font-awesome-4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="webResources/mainPage/css/bootstrap.min.css">
    <link rel="stylesheet" href="webResources/mainPage/css/hero-slider-style.css">
    <link rel="stylesheet" href="webResources/mainPage/css/magnific-popup.css">
    <link rel="stylesheet" href="webResources/mainPage/css/templatemo-style.css">

    <style>
        .myContainer {
            height: 70%;
            overflow-y: auto;
        }

        .mainPageContainer {
            height: 60%;
            overflow-y: auto;
        }
    </style>

    <script>
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
    </script>

    <script>
        function onPageLoad() {
            // 监听 id 为 login 的控件的鼠标左右键点击事件
            $('#login').mousedown(function (e) {
                if (e.which === 1) {
                    // alert('这是左键单击事件');
                    <%
                        if (session.getAttribute("user") == null){
                    %>
                    window.location.href = "pages/login.jsp";
                    <%
                        }
                    %>
                } else if (e.which === 3) {
                    <%
                    if (session.getAttribute("user") != null){
                    %>
                    userLogout();
                    <%
                        }
                    %>
                }
            });
            getInitializeInfo();
            // 先预加载一页 下载页的信息
            getNo1PPTInfo();
            // 监听滚动条是否下拉到最下面
            $("#pptGallery").scroll(function () {
                var nDivHight = $("#pptGallery").height();
                var nScrollHight = $(this)[0].scrollHeight;
                var nScrollTop = $(this)[0].scrollTop;
                if (nScrollTop + nDivHight === nScrollHight) {
                    getNo1PPTInfo();
                }
            });
        }
    </script>

    <script>
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
    </script>

    <script>
        <%-- 一个全局JS变量，用来表示下载页已经下载的页数。JS变量，一刷新，就相当于重新赋值，然后归零 --%>
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
    </script>

</head>
<body onload="onPageLoad()">

<%-- 导航栏 --%>
<div class="cd-hero">
    <div class="cd-slider-nav">
        <nav class="navbar">
            <div class="tm-navbar-bg">
                <a id="login" class="navbar-brand" href="javascript:void(0);"></a>
                <div class="collapse navbar-toggleable-md text-xs-center text-uppercase tm-navbar" id="tmNavbar">
                    <ul class="nav navbar-nav">
                        <li class="nav-item active selected">
                            <a class="nav-link" href="javascript:void(0);" data-no="1">首页</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="javascript:void(0);" data-no="2">下载</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="javascript:void(0);" data-no="3">生成</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="javascript:void(0);" data-no="4">❤</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="javascript:void(0);" data-no="5">❤</a>
                        </li>
                        <li id="myID_0_1" class="nav-item">
                            <a id="myID_0_2" class="nav-link" href="javascript:void(0);" data-no="6"> </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>

    <%-- 第一页，首页，放置一些感性的东西，放置自己喜欢的文章 --%>
    <ul class="cd-hero-slider">
        <li class="selected">
            <div class="cd-full-width">
                <div class="container-fluid js-tm-page-content tm-page-1" style="margin-top: 100px" data-page-no="1">
                    <div class="cd-bg-video-wrapper" data-video="webResources/mainPage/video/moving-cloud"></div>
                    <div class="row">
                        <div class="col-xs-12" style="margin-top: auto">
                            <div class="tm-3-col-container tm-bg-white-translucent">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <h2 id="myID_1_1" class="tm-text-title text-lg-center"></h2>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12 col-sm-12">
                                        <div class="text-xs-left tm-textbox mainPageContainer">
                                            <p id="myID_1_2" class="tm-text"></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </li>


        <%-- 第二页 下载页面 --%>
        <li>
            <div class="cd-full-width">
                <div class="container-fluid js-tm-page-content" data-page-no="2">
                    <div class="cd-bg-video-wrapper" data-video="webResources/mainPage/video/night-light-blur"></div>
                    <div class="tm-img-gallery-container">
                        <div id="pptGallery" class="tm-img-gallery myContainer">
                            <div class="tm-img-gallery-info-container">
                                <h2 class="tm-text-title tm-gallery-title"><span class="tm-white">PPT Gallery</span>
                                </h2>
                                <p class="tm-text">
                                    <span class="tm-white">
                                        这儿是本地仓库中现成的PPT，鼠标悬停在PPT上就能预览，点击即可下载。
                                    </span>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </li>


        <!-- 第三页，生成 - POI核心，爬取图片，用户选择，生成PPT -->
        <li>
            <div class="cd-full-width">
                <div class="container-fluid js-tm-page-content" data-page-no="3">
                    <div class="cd-bg-video-wrapper" data-video="webResources/mainPage/video/sunset-cloud"></div>
                    <div class="tm-img-gallery-container">
                        <div class="tm-img-gallery">
                            <div class="tm-img-gallery-info-container">
                                <h2 class="tm-text-title tm-gallery-title">POI Gallery</h2>
                                <p class="tm-text">这里是使用<a href="http://poi.apache.org/" target="_blank"><span
                                        style="color: #00FFFF">Apache POI</span></a>技术实现PPT模板一键生成的。
                                </p>
                                <div class="col-lg-5">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="搜索一下，你就知道">
                                        <span class="input-group-btn">
                                            <button class="btn btn-group" type="button">Go!</button>
                                        </span>
                                    </div>
                                </div>

                            </div>

                            <div class="grid-item">
                                <a href="webResources/mainPage/img/tm-img-01.jpg">
                                    <img src="webResources/mainPage/img/tm-img-01-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item">
                                <a href="webResources/mainPage/img/tm-img-02.jpg">
                                    <img src="webResources/mainPage/img/tm-img-02-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item">
                                <a href="webResources/mainPage/img/tm-img-03.jpg">
                                    <img src="webResources/mainPage/img/tm-img-03-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item">
                                <a href="webResources/mainPage/img/tm-img-04.jpg">
                                    <img src="webResources/mainPage/img/tm-img-04-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item">
                                <a href="webResources/mainPage/img/tm-img-05.jpg">
                                    <img src="webResources/mainPage/img/tm-img-05-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item">
                                <a href="webResources/mainPage/img/tm-img-06.jpg">
                                    <img src="webResources/mainPage/img/tm-img-06-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item">
                                <a href="webResources/mainPage/img/tm-img-07.jpg">
                                    <img src="webResources/mainPage/img/tm-img-07-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item">
                                <a href="webResources/mainPage/img/tm-img-08.jpg">
                                    <img src="webResources/mainPage/img/tm-img-08-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </li>


        <%-- 第四页，生成页面 - POI核心 --%>
        <li>
            <div class="cd-full-width">
                <div class="container-fluid js-tm-page-content" data-page-no="4">
                    <div class="cd-bg-video-wrapper" data-video="webResources/mainPage/video/red-flower"></div>
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="tm-flex tm-2-col-container-2">
                                <div class="tm-bg-white-translucent text-xs-left tm-textbox tm-2-col-textbox-2 tm-textbox-padding">
                                    <h2 class="tm-text-title">Lorem ipsum dolor</h2>
                                    <p class="tm-text">Nulla efficitur, ligula et imperdiet volutpat, lacus tortor
                                        tempus massa, eget tempus quam nibh vel nulla.</p>
                                    <p class="tm-text">Vivamus non molestie leo, non tincidunt diam. Mauris sagittis
                                        elit in velit ultricies aliquet sed in magna.</p>
                                    <p class="tm-text">Pellentesque semper, est nec consequat viverra, sem augue
                                        tincidunt nisi, a posuere nisi sapien sed sapien. Nulla facilisi.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </li>

        <%-- 第五页 三列 --%>
        <li>
            <div class="cd-full-width">
                <div class="container-fluid js-tm-page-content" data-page-no="5">
                    <div class="cd-bg-video-wrapper" data-video="webResources/mainPage/video/padaut-bee"></div>
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="tm-flex tm-3-col-container">

                                <div class="tm-3-col-textbox tm-bg-white-translucent">
                                    <div class="text-xs-left tm-textbox tm-textbox-padding">

                                        <h2 class="tm-text-title">Testimonial One</h2>
                                        <p class="tm-text">Etiam vitae imperdiet magna. Vestibulum blandit vehicula
                                            metus, ac ornare eros elementum et. Pellentesque habitant morbi tristique
                                            senectus et ntus et malesuada fames ac turpis egestas.</p>
                                        <p class="tm-text">Mauris lobortis lorem nulla, non tristique enim sollicitudin
                                            eu. Praesent tempus dapibus odio nec elementum.</p>
                                    </div>
                                </div>
                                <div class="tm-3-col-textbox tm-bg-white-translucent">
                                    <div class="text-xs-left tm-textbox tm-textbox-padding">

                                        <h2 class="tm-text-title">Testimonial Two</h2>

                                        <p class="tm-text">Curabitur sodales, est auctor congue vulputate, nisl tellus
                                            finibus nunc, vitae consectetur enim erat vitae quam.</p>

                                        <p class="tm-text">Pellentesque habitant morbi tristique senectus et netus et
                                            malesuada fames ac turpis egestas. Nunc vitae tempor turpis.</p>
                                    </div>
                                </div>

                                <div class="tm-3-col-textbox tm-bg-white-translucent">
                                    <div class="text-xs-left tm-textbox tm-textbox-padding">

                                        <h2 class="tm-text-title">Testimonial Three</h2>

                                        <p class="tm-text">Mauris lobortis lorem nulla, non tristique enim sollicitudin
                                            eu. Praesent tempus dapibus odio nec elementum.</p>

                                        <p class="tm-text">Sed elementum est quis tortor faucibus, et molestie nibh
                                            finibus. Mauris condimentum ex vestibulum fringilla consectetur.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </li>


        <%-- 第六页 设置首页文章 --%>
        <li>
            <div class="cd-full-width">
                <div class="container-fluid js-tm-page-content" data-page-no="6">
                    <div class="cd-bg-video-wrapper" data-video="webResources/mainPage/video/sunset-loop"></div>
                    <div class="tm-contact-page">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="tm-flex tm-contact-container">
                                    <div class="tm-bg-white-translucent text-xs-left tm-textbox tm-2-col-textbox-2 tm-textbox-padding tm-textbox-padding-contact">
                                        <p class="tm-text text-xs-center">
                                            <span style="font-family: 'Microsoft YaHei UI',monospace;font-size: 3ex">在这里，你可以个性化的定制首页的文章显示</span>
                                        </p>
                                        <div class="tm-contact-form">
                                            <div class="form-group">
                                                <input type="text" id="essay_title" title="在这里填写文章标题"
                                                       class="form-control myFonts" placeholder="标题"/>
                                            </div>
                                            <div class="form-group">
                                                <textarea id="essay_content" class="form-control" rows="5"
                                                          title="在这里填写文本内容"
                                                          placeholder="内容"></textarea>
                                            </div>
                                            <button class="pull-xs-right tm-submit-btn"
                                                    onclick="setMainPageEssay()">发送
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </li>
    </ul>

    <footer class="tm-footer">
        <%--<div class="tm-social-icons-container text-xs-center">--%>
        <%--<a href="#" class="tm-social-link"><i class="fa fa-facebook"></i></a>--%>
        <%--<a href="#" class="tm-social-link"><i class="fa fa-google-plus"></i></a>--%>
        <%--<a href="#" class="tm-social-link"><i class="fa fa-twitter"></i></a>--%>
        <%--<a href="#" class="tm-social-link"><i class="fa fa-behance"></i></a>--%>
        <%--<a href="#" class="tm-social-link"><i class="fa fa-linkedin"></i></a>--%>
        <%--</div>--%>
        <div class="text-xs-center">
            <p class="tm-copyright-text text-xs-center">Copyright &copy; 2017-2018
                <a href="https://github.com/Zereao" target="_blank" title="访问我的GitHub"> 白露</a>
                All Rights Reserved</p>
        </div>
    </footer>

</div>

<div id="loader-wrapper">
    <div id="loader"></div>
    <div class="loader-section section-left"></div>
    <div class="loader-section section-right"></div>
</div>
</body>
</html>
