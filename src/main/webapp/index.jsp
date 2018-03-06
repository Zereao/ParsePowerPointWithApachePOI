<%--
  Created by IntelliJ IDEA.
  User: Jupiter
  Date: 2018/2/23
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.parse.ppt.poi.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.parse.ppt.poi.entity.No1PPT" %>
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
        .myFonts {
            font-family: 黑体, sans-serif;
        }
    </style>

    <script>
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

            <%
                if (session.getAttribute("user") == null){
            %>
            loadUserFromCookies();
            <%
                }
            %>

        }

        function loadUserFromCookies() {
            $.ajax({
                type: "post",
                url: "/login/loadUserFromCookies",
                produces: "text/html;charset=UTF-8",
                error: function (request) {
                    alert("访问后端出现未知错误！");
                },
                success: function (data) {
                    var result = data.toString();
                    if (result === "SUCCESS") {
                        location.reload();
                    }
                }
            });
        }

        $(document).ready(function () {
            var pptGallerySelector = $("#pptGallery");
            var nScrollHight = 0; //滚动距离总长(注意不是滚动条的长度)
            var nScrollTop = 0;   //滚动到的当前位置
            var nDivHight = pptGallerySelector.height();
            pptGallerySelector.scroll(function () {
                nScrollHight = $(this)[0].scrollHeight;
                nScrollTop = $(this)[0].scrollTop;
                if (nScrollTop + nDivHight >= nScrollHight) {
                    alert("滚动条到底部了");
                }
            });
        });
    </script>

    <script>
        function getNo1PPTInfo() {
            alert("进入getNo1PPTInfo 函数");
            $.ajax({
                type: "post",
                url: "/download/loadNo1PPT",
                produces: "text/html;charset=UTF-8",
                async: false,
                error: function (request) {
                    alert("获取PPT失败！");
                },
                success: function (data) {
                    for (var i = 0; i < data.length; i++) {

                    }
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
                    error: function (request) {
                        alert("访问后端出现未知错误！");
                    },
                    success: function (data) {
                        alert("❤注销成功，期待再会❤");
                        location.reload();
                    }
                });
            }
        }

        function ajaxAddAboutPageInfo() {
            var aboutPageInfo = {
                user_name: $("#about_user_name").val(),
                e_mail: $("#about_email").val(),
                summary: $("#about_summary").val(),
                description: $("#about_description").val()
            };
            $.ajax({
                type: "post",
                dataType: "text",
                url: "/mainPage/servlet/AboutPageInfoServlet",
                produces: "text/html;charset=UTF-8",
                data: aboutPageInfo,
                error: function (request) {
                    alert("Connection error");
                },
                success: function (data) {
                    alert("❤发送成功，谢谢来信❤")
//                        tag = JSON.parse(data);
                }
            });
        }
    </script>
</head>
<body onload="onPageLoad()">
<%
    String welcomeWord = "Hi,Melody";
    String welcomeTitle = "点我登录/注册";
    String username = "";
    String isHidden = "hidden";
    User user = (User) session.getAttribute("user");
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    System.out.println("从session中获取到用户信息： the user of session = " + user);
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    if (user != null) {
        username = user.getUsername();
        welcomeTitle = "欢迎回来，亲爱的" + username + "。右键点击退出登录";
    }
    if (!("".equals(username)) && username != null) {
        welcomeWord = "Hi," + username;
        isHidden = "";
    }
%>

<div class="cd-hero">
    <div class="cd-slider-nav">
        <nav class="navbar">
            <div class="tm-navbar-bg">
                <a id="login" class="navbar-brand" href="javascript:void(0);"
                   title="<%=welcomeTitle%>">
                    <i class="fa fa-send-o tm-brand-icon"></i><%=welcomeWord%>
                </a>
                <div class="collapse navbar-toggleable-md text-xs-center text-uppercase tm-navbar" id="tmNavbar">
                    <ul class="nav navbar-nav">
                        <li class="nav-item active selected">
                            <!--<a class="nav-link" href="#0" data-no="1">首页<span class="sr-only">sss</span></a>-->
                            <a class="nav-link" href="javascript:void(0);" data-no="1">首页</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" onclick="" data-no="2">下载</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="javascript:void(0);" data-no="3">❤</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="javascript:void(0);" data-no="4">❤</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="javascript:void(0);" data-no="5">❤</a>
                        </li>
                        <li class="nav-item" <%=isHidden%>>
                            <a class="nav-link" href="javascript:void(0);" data-no="6"><%=username%>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

        </nav>
    </div>

    <ul class="cd-hero-slider">
        <li class="selected">
            <div class="cd-full-width">
                <div class="container-fluid js-tm-page-content tm-page-1" data-page-no="1">

                    <div class="cd-bg-video-wrapper" data-video="webResources/mainPage/video/moving-cloud">
                        <!-- video element will be loaded using jQuery -->
                    </div> <!-- .cd-bg-video-wrapper -->

                    <div class="row">

                        <div class="col-xs-12">
                            <div class="tm-2-col-container tm-bg-white-translucent">

                                <div class="row">
                                    <div class="col-xs-12">
                                        <h2 class="tm-text-title">故事长满天涯海角，包括你和你的故乡</h2>
                                    </div>
                                </div>

                                <p class="tm-text"></p>
                                <!--  <div class="row">
                                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xl-6 tm-2-col-left">
                                        <div class="text-xs-left tm-textbox tm-2-col-textbox">
                                            <p class="tm-text">Motion web template integrates a very active video background for each page. Download and use this for your website and tell your friends about it.</p>
                                            <p class="tm-text">This HTML CSS template is brought to you by <a href="http://plus.google.com/+templatemo" target="_blank">templatemo</a>. You can fully customize it to meet your website needs.</p>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xl-6 tm-2-col-right">
                                        <div class="text-xs-left tm-textbox tm-2-col-textbox">
                                            <p class="tm-text">Cras tempus, eros vel ultrices aliquam, velit tortor sodales risus, ac facilisis lectus tortor eget neque. Nam auctor dui ante. Curabitur tristique.</p>
                                            <p class="tm-text">Quisque sagittis quam tortor, sit amet posuere justo tempor non. Nunc eu leo sit amet elit condimentum.</p>
                                        </div>
                                    </div>
                                </div>-->
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
                        <div id="pptGallery" class="tm-img-gallery">
                            <div class="tm-img-gallery-info-container">
                                <h2 class="tm-text-title tm-gallery-title"><span class="tm-white">PPT Gallery</span>
                                </h2>
                                <p class="tm-text">
                                    <span class="tm-white">
                                        这儿是使用爬虫技术从<a href="http://www.1ppt.com/" target="_blank">第一PPT</a>网站上直接爬取得到的相关PPT的信息，点击相关的图片就能直接下载。
                                    </span>
                                </p>
                            </div>

                            <%--<!-- 商品循环开始 -->--%>
                            <%--<%--%>
                            <%--System.out.println("11111111111111111111111111111111111");--%>
                            <%--Object pptListObj = session.getAttribute("pptList");--%>
                            <%--boolean pptListIsNull = pptListObj == null;--%>
                            <%--if (!pptListIsNull) {--%>
                            <%--//noinspection unchecked--%>
                            <%--List<No1PPT> pptList = (List<No1PPT>) pptListObj;--%>
                            <%--for (No1PPT ppt : pptList) {--%>
                            <%--%>--%>
                            <%--<div class="grid-item" title="<%=ppt.getSrcDescription()%>">--%>
                            <%--<a href="<%=ppt.getDownloadUrl()%>">--%>
                            <%--<img src="<%=ppt.getSrcImgUrl()%>" alt="Image"--%>
                            <%--class="img-fluid tm-img">--%>
                            <%--</a>--%>
                            <%--</div>--%>
                            <%--<%--%>
                            <%--}--%>
                            <%--}--%>
                            <%--%>--%>

                            <div class="grid-item" title="鼠标悬停">
                                <a href="webResources/mainPage/img/tm-img-09.jpg">
                                    <img src="webResources/mainPage/img/tm-img-09-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item" title="鼠标悬停">
                                <a href="webResources/mainPage/img/tm-img-09.jpg">
                                    <img src="webResources/mainPage/img/tm-img-09-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item" title="鼠标悬停">
                                <a href="webResources/mainPage/img/tm-img-09.jpg">
                                    <img src="webResources/mainPage/img/tm-img-09-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item" title="鼠标悬停">
                                <a href="webResources/mainPage/img/tm-img-09.jpg">
                                    <img src="webResources/mainPage/img/tm-img-09-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item" title="鼠标悬停">
                                <a href="webResources/mainPage/img/tm-img-09.jpg">
                                    <img src="webResources/mainPage/img/tm-img-09-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item" title="鼠标悬停">
                                <a href="webResources/mainPage/img/tm-img-09.jpg">
                                    <img src="webResources/mainPage/img/tm-img-09-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item" title="鼠标悬停">
                                <a href="webResources/mainPage/img/tm-img-09.jpg">
                                    <img src="webResources/mainPage/img/tm-img-09-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item" title="鼠标悬停">
                                <a href="webResources/mainPage/img/tm-img-09.jpg">
                                    <img src="webResources/mainPage/img/tm-img-09-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>

                        </div>

                    </div> <!-- .tm-img-gallery-container -->

                </div> <!-- .container-fluid -->

            </div> <!-- .cd-full-width -->

        </li>
        <%--<div class="copyrights">Collect from <a href="http://www.cssmoban.com/">企业网站模板</a></div>--%>
        <!-- Page 3 -->
        <li>

            <div class="cd-full-width">

                <div class="container-fluid js-tm-page-content" data-page-no="3">

                    <div class="cd-bg-video-wrapper" data-video="webResources/mainPage/video/sunset-cloud">
                        <!-- video element will be loaded using jQuery -->
                    </div> <!-- .cd-bg-video-wrapper -->

                    <div class="tm-img-gallery-container">

                        <div class="tm-img-gallery gallery-one">
                            <!-- Gallery One pop up connected with JS code below -->

                            <div class="tm-img-gallery-info-container">

                                <h2 class="tm-text-title tm-gallery-title">Gallery One</h2>
                                <p class="tm-text">Nulla efficitur, ligula et imperdiet volutpat, lacus tortor tempus
                                    massa, eget tempus quam
                                    nibh vel nulla. Maecenas purus sem, lobortis id odio in, ultrices scelerisque
                                    sapien.
                                </p>

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

                    </div> <!-- .tm-img-gallery-container -->

                </div> <!-- .container-fluid -->

            </div> <!-- .cd-full-width -->

        </li>

        <!-- Page 4 -->
        <li>

            <div class="cd-full-width">

                <div class="container-fluid js-tm-page-content" data-page-no="4">

                    <div class="cd-bg-video-wrapper" data-video="webResources/mainPage/video/red-flower">
                        <!-- video element will be loaded using jQuery -->
                    </div> <!-- .cd-bg-video-wrapper -->

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

                                <div class="tm-bg-white-translucent text-xs-left tm-textbox tm-2-col-textbox-2 tm-textbox-padding">
                                    <h2 class="tm-text-title">Aliquam sem sem</h2>
                                    <p class="tm-text">Proin sagittis mauris dolor, vel efficitur lectus dictum nec. Sed
                                        ultrices placerat arcu, id malesuada metus cursus suscipit. Donex quis
                                        consectetur ligula. Proin accumsan eros id nisi porttitor, a facilisis quam
                                        cursus.</p>
                                    <p class="tm-text">Donec vitae bibendum est, et ultrices urna. Curabitur ac bibendum
                                        augue, a convallis mi. Cum sociis natoque penatibus et magnis dis parturient
                                        montes, nascetur ridiculus mus. Mauris consequat metus hendrerit, tincidunt mi
                                        nec, euismod massa.</p>
                                </div>

                            </div>
                        </div>
                    </div>

                </div>

            </div> <!-- .cd-full-width -->


        </li>

        <li>

            <div class="cd-full-width">
                <div class="container-fluid js-tm-page-content" data-page-no="5">

                    <div class="cd-bg-video-wrapper" data-video="webResources/mainPage/video/padaut-bee">
                        <!-- video element will be loaded using jQuery -->
                    </div> <!-- .cd-bg-video-wrapper -->

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

        <li>
            <div class="cd-full-width">

                <div class="container-fluid js-tm-page-content" data-page-no="6">

                    <div class="cd-bg-video-wrapper" data-video="webResources/mainPage/video/sunset-loop">
                        <!-- video element will be loaded using jQuery -->
                    </div> <!-- .cd-bg-video-wrapper -->

                    <div class="tm-contact-page">

                        <div class="row">

                            <div class="col-xs-12">

                                <div class="tm-flex tm-contact-container">

                                    <div class="tm-bg-white-translucent text-xs-left tm-textbox tm-2-col-textbox-2 tm-textbox-padding tm-textbox-padding-contact">
                                        <p class="tm-text text-xs-center">
                                                    <span style="font-family: 'Microsoft YaHei UI',monospace;font-size: 3ex">
                                                        给我留言吧~
                                                    </span>
                                        </p>

                                        <!-- contact form -->
                                        <div class="tm-contact-form">

                                            <div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xl-6 tm-form-group-left">
                                                <input type="text" id="about_user_name" name="user_name"
                                                       class="form-control myFonts" placeholder="姓名" required/>
                                            </div>

                                            <div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xl-6 tm-form-group-right">
                                                <input type="text" id="about_email" name="e_mail"
                                                       class="form-control myFonts" placeholder="电子邮箱" required/>
                                            </div>

                                            <div class="form-group">
                                                <input type="text" id="about_summary" name="summary"
                                                       class="form-control myFonts" placeholder="简单摘要" required/>
                                            </div>

                                            <div class="form-group">
                                                <textarea id="about_description" name="description"
                                                          class="form-control myFonts" rows="5"
                                                          placeholder="详细描述"></textarea>
                                            </div>

                                            <button class="pull-xs-right tm-submit-btn"
                                                    onclick="ajaxAddAboutPageInfo()">发送
                                            </button>

                                        </div>
                                    </div>

                                    <div class="tm-bg-white-translucent text-xs-left tm-textbox tm-2-col-textbox-2 tm-textbox-padding tm-textbox-padding-contact">
                                        <h2 class="tm-contact-info">疏影横斜水清浅，暗香浮动月黄昏</h2>
                                        <!-- google map goes here -->
                                        <div id="">疏影横斜水清浅，暗香浮动月黄昏</div>
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
        <div class="tm-social-icons-container text-xs-center">
            <a href="#" class="tm-social-link"><i class="fa fa-facebook"></i></a>
            <a href="#" class="tm-social-link"><i class="fa fa-google-plus"></i></a>
            <a href="#" class="tm-social-link"><i class="fa fa-twitter"></i></a>
            <a href="#" class="tm-social-link"><i class="fa fa-behance"></i></a>
            <a href="#" class="tm-social-link"><i class="fa fa-linkedin"></i></a>
        </div>
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
