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
    <link rel="shortcut  icon" type="image/x-icon" href="webResources/favicon/sun.ico" media="screen"/>
    <title>故事长满天涯海角，包括你和你的故乡。</title>

    <script src="webResources/mainPage/js/html5shiv.min.js"></script>
    <script src="webResources/mainPage/js/respond.min.js"></script>
    <script src="webResources/mainPage/js/jquery-1.11.3.min.js"></script>
    <script src="webResources/mainPage/js/tether.min.js"></script>
    <script src="webResources/mainPage/js/bootstrap.min.js"></script>
    <script src="webResources/mainPage/js/hero-slider-main.js"></script>
    <script src="webResources/mainPage/js/masonry.pkgd.min.js"></script>
    <script src="webResources/mainPage/js/jquery.magnific-popup.min.js"></script>

    <%-- 导入分离出的JavaScript --%>
    <script src="webResources/mainPage/js/index.jsp.js"></script>

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

        .myInputBoxStyle {
            border-top-right-radius: 8px;
            border-top-left-radius: 8px;
            border-bottom-right-radius: 8px;
            border-bottom-left-radius: 8px;
        }
    </style>

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
                        <div id="poiGallery" class="tm-img-gallery myContainer">
                            <div class="tm-img-gallery-info-container">
                                <h2 class="tm-text-title tm-gallery-title">POI Gallery</h2>
                                <div class="col-lg-10">
                                    <div class="input-group input-group-lg">
                                        <input type="text" class="form-control myInputBoxStyle"
                                               placeholder="输入关键词，使用Apache POI技术实现PPT模板一键生成">
                                        <span class="input-group-btn">
                                            <button class="btn btn-secondary btn-lg" type="button">Go!</button>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <div class="grid-item">
                                <a href="webResources/mainPage/img/tm-img-08.jpg">
                                    <img src="webResources/mainPage/img/tm-img-08-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item">
                                <a href="webResources/mainPage/img/tm-img-08.jpg">
                                    <img src="webResources/mainPage/img/tm-img-08-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item">
                                <a href="webResources/mainPage/img/tm-img-08.jpg">
                                    <img src="webResources/mainPage/img/tm-img-08-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item">
                                <a href="webResources/mainPage/img/tm-img-08.jpg">
                                    <img src="webResources/mainPage/img/tm-img-08-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item">
                                <a href="webResources/mainPage/img/tm-img-08.jpg">
                                    <img src="webResources/mainPage/img/tm-img-08-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item">
                                <a href="webResources/mainPage/img/tm-img-08.jpg">
                                    <img src="webResources/mainPage/img/tm-img-08-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item">
                                <a href="webResources/mainPage/img/tm-img-08.jpg">
                                    <img src="webResources/mainPage/img/tm-img-08-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item">
                                <a href="webResources/mainPage/img/tm-img-08.jpg">
                                    <img src="webResources/mainPage/img/tm-img-08-tn.jpg" alt="Image"
                                         class="img-fluid tm-img">
                                </a>
                            </div>
                            <div class="grid-item">
                                <a href="webResources/mainPage/img/tm-img-08.jpg">
                                    <img src="webResources/mainPage/img/tm-img-08-tn.jpg" alt="Image"
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
