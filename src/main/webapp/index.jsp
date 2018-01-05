<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">

    <!--网站图标-->
    <link rel="shortcut icon" type="image/x-icon" href="webResources/favicon/sun.ico" media="screen"/>
    <!--网站名称-->
    <title>故事长满天涯海角，包括你和你的故乡。</title>

    <script src="webResources/js/jquery-3.2.1.min.js"></script>
    <script src="webResources/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="webResources/css/bootstrap.min.css"/>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <!--<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>-->
    <!--<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>-->
    <!--<![endif]&ndash;&gt;-->

    <!-- Magnific popup (http://dimsemenov.com/plugins/magnific-popup/) -->


    <style>
        html {
            margin: 0;
            padding: 0;
            width: 100%;
            height: 100%;
            overflow: hidden;
            background: black;
            background: linear-gradient(to bottom, #dcdcdc 0%, #cfdbc0 100%);
        }

        body {
            margin: 0;
            padding: 0;
            width: 100%;
            height: 100%;
            overflow: hidden;
            background: black;
            background: url("webResources/image/main_bg.jpg");
        }

        #main-canvas {
            width: 100%;
            height: 100%;
        }

        .filter {
            width: 100%;
            height: 100%;
            position: absolute;
            top: 0;
            left: 0;
            background: #fe5757;
            animation: colorChange 30s ease-in-out infinite;
            animation-fill-mode: both;
            mix-blend-mode: overlay;

        }

        .searchBox {
            position: absolute;
            top: 150px;
        }

        @keyframes colorChange {
            0%, 100% {
                opacity: 0;
            }
            50% {
                opacity: .7;
            }
        }
    </style>

</head>
<body>
<div>
    <canvas id="canvas" style="z-index: 50"></canvas>

    <div class="input-group col-md-4 col-md-offset-4 searchBox " style="z-index: 1000">
        <input type="text" class="form-control" id="searchBoxID" name="searchBoxKeyWord" onclick="" placeholder="请输入关键词，多个关键词用空格隔开"/>
        <span class="input-group-btn">
               <button class="btn btn-info btn-search">搜索</button>
        </span>
    </div>
</div>

<%--自定义动画的 JavaScript ,必须定义在上面这条语句之后，原因是下面这个 JS 的第32行 【canvas = document.getElementById('canvas')】 获取了上面这个ID--%>
<script src="webResources/js/canvasstar.js"></script>

<script>
    var CanvasStar = new CanvasStar;
    CanvasStar.init();
</script>

</body>
</html>