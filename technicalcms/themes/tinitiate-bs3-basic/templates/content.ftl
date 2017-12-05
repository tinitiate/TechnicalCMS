<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="../common/favicon.ico">
    <!-- Page Meta tags -->
    <title>${Title}</title>
    <meta name="description" content="${MetaDescription}">
    <meta name="keywords" content="${MetaKeywords}">
    <meta name="author" content="${Author}">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" href="../common/css/xcode.css">
    <link rel="stylesheet" href="../common/css/tinitiate.css">

  </head>
  <body style="padding:0;margin:0;">
    <!-- Google Analytics -->
    <div id="googleanalytics"></div>

    <!-- NavBar -->
    <div id="globalnav"></div>
    <br><br>
        <div id="container">
            <!-- JumboTron Content Start -->
            <div class="jumbotron subhead" id="overview" align="center" style="background-color:#3A83C2; color:white;">
            <h1 style="color:yellow">${Title}</h1>
            </div>
            <!-- JumboTron Content Start -->
            <div id="inPage1"></div>
            <!-- row-offcanvas START  -->
            <div class="row-offcanvas row-offcanvas-left">
                <div id="sidebar" class="sidebar-offcanvas">
                    <div id="sidenavbar"></div>
                </div>
                <!-- Main Body START -->
                <div id="main">
                    <!-- MD-12 START -->
                    <div class="col-md-12">
                        <p class="visible-xs">
                        <button type="button" class="btn btn-link btn-xs" data-toggle="offcanvas"><i class="fa fa-arrow-circle-o-left fa-2x"></i> MENU</button>
                        </p>
                        <div id="breadcrumb"></div>
                        <div id="respad1"></div>
${HTML}
                    <!-- MD-12 START -->
                    </div>
                    <br><br>
                <!-- Main Body END -->
                </div>
            <!--/row-offcanvas -->
            </div>
        <div id="respad2"></div>
        <!-- Main Container START -->
        </div>
    <!-- Global Footer -->
    <div id="globalfooter"></div>

    <!-- All JS References -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://static.addtoany.com/menu/page.js"></script>
    <script src="../common/js/highlight.pack.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
    <!-- JS Scripts -->
    <script>
        (function() {
        var cx = '008362622423049463333:wg_5u9jmzau';
        var gcse = document.createElement('script');
        gcse.type = 'text/javascript';
        gcse.async = true;
        gcse.src = 'https://cse.google.com/cse.js?cx=' + cx;
        var s = document.getElementsByTagName('script')[0];
        s.parentNode.insertBefore(gcse, s);
        })();
    </script>

    <script src="foldernav.js"></script>
    <script src="../common/js/tinitiate.js"></script>
    <script src="../common/js/ti.js"></script>
    <script src="../common/js/ti_dev.js"></script>

    <script>
        // BreadCrumb data
        l_breadcrumb_json = ${BreadCrumbJSON}
        $("#breadcrumb").html(breadCrumbCreator(l_breadcrumb_json));

        // Side Nav bar generation
        $("#sidenavbar").html(sidebarCreator(foldenavjson));
    </script>
    </body>
</html>