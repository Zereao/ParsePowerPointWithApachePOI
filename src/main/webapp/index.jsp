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

    <!-- load stylesheets -->
    <!--<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400">-->
    <!--<link rel="stylesheet" href="webResources/mainPage/css/GoogleOpenSansFonts.css">-->
    <!-- Google web font "Open Sans" -->
    <link rel="stylesheet" href="webResources/mainPage/font-awesome-4.5.0/css/font-awesome.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="webResources/mainPage/css/bootstrap.min.css">
    <!-- Bootstrap style -->
    <link rel="stylesheet" href="webResources/mainPage/css/hero-slider-style.css">
    <!-- Hero slider style (https://codyhouse.co/gem/hero-slider/) -->
    <link rel="stylesheet" href="webResources/mainPage/css/magnific-popup.css">
    <!-- Magnific popup style (http://dimsemenov.com/plugins/magnific-popup/) -->
    <link rel="stylesheet" href="webResources/mainPage/css/templatemo-style.css">
    <!-- Templatemo style -->

    <!-- load JS files -->
    <script src="webResources/mainPage/js/html5shiv.min.js"></script>
    <script src="webResources/mainPage/js/respond.min.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <!--<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>-->
    <!--<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>-->
    <!--<![endif]&ndash;&gt;-->

    <script src="webResources/mainPage/js/jquery-1.11.3.min.js"></script>
    <script src="webResources/mainPage/js/tether.min.js"></script>
    <script src="webResources/mainPage/js/bootstrap.min.js"></script>
    <script src="webResources/mainPage/js/hero-slider-main.js"></script>
    <script src="webResources/mainPage/js/masonry.pkgd.min.js"></script>
    <script src="webResources/mainPage/js/jquery.magnific-popup.min.js"></script>

    <style>
        .myFonts {
            font-family: 黑体, sans-serif;
        }
    </style>
</head>
<body>
<%
    String username = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length > 0) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase("username")) {
                username = cookie.getValue();
            }
        }
    }
//    String user = (String) request.getSession().getAttribute("username");
//    if (!("".equals(user)) && user != null) {
//        username = user;
//    }
    String welcomeWord = "Hi,Melody";
    if (!("".equals(username)) && username != null) {
        welcomeWord = "Hi," + username;
    }
%>

<!-- Content -->
<div class="cd-hero">
    <!-- Navigation -->
    <div class="cd-slider-nav">
        <nav class="navbar">
            <div class="tm-navbar-bg">

                <!--<a class="navbar-brand text-uppercase" href="#">-->
                <a class="navbar-brand" href="pages/login.jsp" title="点我登陆/注册">

                    <i class="fa fa-send-o tm-brand-icon"></i><%=welcomeWord%>
                </a>

                <!--<button class="navbar-toggler hidden-lg-up" type="button" data-toggle="collapse" data-target="#tmNavbar">-->
                <!--&#9776;-->
                <!--</button>-->
                <div class="collapse navbar-toggleable-md text-xs-center text-uppercase tm-navbar" id="tmNavbar">
                    <ul class="nav navbar-nav">
                        <li class="nav-item active selected">
                            <!--<a class="nav-link" href="#0" data-no="1">首页<span class="sr-only">sss</span></a>-->
                            <a class="nav-link" href="#0" onclick="" data-no="1">首页</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#0" data-no="2">博客</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#0" data-no="3">❤</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#0" data-no="4">❤</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#0" data-no="5">❤</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#0" data-no="6">关于</a>
                        </li>
                    </ul>
                </div>
            </div>

        </nav>
    </div>

    <ul class="cd-hero-slider">  <!-- autoplay -->
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

                                <p class="tm-text">&emsp;&emsp;Motion web template integrates a very active video
                                    background for each page. Download and use this for your website and tell your
                                    friends about it.<br>
                                    &nbsp;This HTML CSS template is brought to you by <a
                                            href="http://plus.google.com/+templatemo" target="_blank">templatemo</a>.
                                    You can fully customize it to meet your website needs.</p>
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
            </div> <!-- .cd-full-width -->
        </li>

        <li>
            <div class="cd-full-width">

                <div class="container-fluid js-tm-page-content" data-page-no="2">

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
        <div class="copyrights">Collect from <a href="http://www.cssmoban.com/">企业网站模板</a></div>
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

                    <div class="cd-bg-video-wrapper" data-video="webResources/mainPage/video/night-light-blur">
                        <!-- video element will be loaded using jQuery -->
                    </div> <!-- .cd-bg-video-wrapper -->

                    <div class="tm-img-gallery-container">

                        <div class="tm-img-gallery gallery-two">
                            <!-- Gallery Two pop up connected with JS code below -->

                            <div class="tm-img-gallery-info-container">

                                <h2 class="tm-text-title tm-gallery-title"><span class="tm-white">Gallery Two</span>
                                </h2>
                                <p class="tm-text"><span class="tm-white">Nulla efficitur, ligula et imperdiet volutpat, lacus tortor tempus massa, eget tempus quam nibh vel nulla. Maecenas purus sem, lobortis id odio in, ultrices scelerisque sapien.</span>
                                </p>

                            </div>

                            <div class="grid-item">
                                <a href="webResources/mainPage/img/tm-img-09.jpg">
                                    <img src="webResources/mainPage/img/tm-img-09-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item">
                                <a href="webResources/mainPage/img/tm-img-10.jpg">
                                    <img src="webResources/mainPage/img/tm-img-10-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item">
                                <a href="webResources/mainPage/img/tm-img-11.jpg">
                                    <img src="webResources/mainPage/img/tm-img-11-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item">
                                <a href="webResources/mainPage/img/tm-img-12.jpg">
                                    <img src="webResources/mainPage/img/tm-img-12-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item">
                                <a href="webResources/mainPage/img/tm-img-13.jpg">
                                    <img src="webResources/mainPage/img/tm-img-13-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item">
                                <a href="webResources/mainPage/img/tm-img-14.jpg">
                                    <img src="webResources/mainPage/img/tm-img-14-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item">
                                <a href="webResources/mainPage/img/tm-img-15.jpg">
                                    <img src="webResources/mainPage/img/tm-img-15-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item">
                                <a href="webResources/mainPage/img/tm-img-16.jpg">
                                    <img src="webResources/mainPage/img/tm-img-16-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                        </div>

                    </div> <!-- .tm-img-gallery-container -->

                </div> <!-- .container-fluid -->

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

            </div> <!-- .cd-full-width -->
        </li>
    </ul> <!-- .cd-hero-slider -->

    <footer class="tm-footer">

        <div class="tm-social-icons-container text-xs-center">
            <a href="#" class="tm-social-link"><i class="fa fa-facebook"></i></a>
            <a href="#" class="tm-social-link"><i class="fa fa-google-plus"></i></a>
            <a href="#" class="tm-social-link"><i class="fa fa-twitter"></i></a>
            <a href="#" class="tm-social-link"><i class="fa fa-behance"></i></a>
            <a href="#" class="tm-social-link"><i class="fa fa-linkedin"></i></a>
        </div>
        <div class="text-xs-center">
            <p class="tm-copyright-text text-xs-center">Copyright &copy; 2016-2017
                <a href="https://github.com/Zereao" target="_blank" title="访问我的GitHub"> 白露</a>
                All Rights Reserved</p>
        </div>
    </footer>

</div> <!-- .cd-hero -->


<!-- Preloader, https://ihatetomatoes.net/create-custom-preloading-screen/ -->
<div id="loader-wrapper">

    <div id="loader"></div>
    <div class="loader-section section-left"></div>
    <div class="loader-section section-right"></div>

</div>


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

        // Get the page height
        var totalPageHeight = 15 + $('.cd-slider-nav').height()
            + pageContentHeight + offset
            + $('.tm-footer').height();

        // Adjust layout based on page height and window height
        if (totalPageHeight > $(window).height()) {
            $('.cd-hero-slider').addClass('small-screen');
            $('.cd-hero-slider li:nth-of-type(' + pageNo + ')').css("min-height", totalPageHeight + "px");
        }
        else {
            $('.cd-hero-slider').removeClass('small-screen');
            $('.cd-hero-slider li:nth-of-type(' + pageNo + ')').css("min-height", "100%");
        }
    }

    /*
        Everything is loaded including images.
    */
    $(window).load(function () {

        adjustHeightOfPage(1); // Adjust page height

        /* Gallery One pop up
        -----------------------------------------*/
        $('.gallery-one').magnificPopup({
            delegate: 'a', // child items selector, by clicking on it popup will open
            type: 'image',
            gallery: {enabled: true}
        });

        /* Gallery Two pop up
        -----------------------------------------*/
        $('.gallery-two').magnificPopup({
            delegate: 'a',
            type: 'image',
            gallery: {enabled: true}
        });

        /* Collapse menu after click
        -----------------------------------------*/
        $('#tmNavbar a').click(function () {
            $('#tmNavbar').collapse('hide');

            adjustHeightOfPage($(this).data("no")); // Adjust page height
        });

        /* Browser resized
        -----------------------------------------*/
        $(window).resize(function () {
            var currentPageNo = $(".cd-hero-slider li.selected .js-tm-page-content").data("page-no");

            // wait 3 seconds
            setTimeout(function () {
                adjustHeightOfPage(currentPageNo);
            }, 1000);

        });

        // Remove preloader (https://ihatetomatoes.net/create-custom-preloading-screen/)
        $('body').addClass('loaded');

    });


</script>

<script>
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
</body>
</html>
