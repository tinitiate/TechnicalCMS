$(document).ready(function() {
  $('[data-toggle=offcanvas]').click(function() {
    $('.row-offcanvas').toggleClass('active');
  });
});

$("#socialbuttons").html('\
<div class="panel panel-default"> \
    <div class="panel-body"> \
    <div class="a2a_kit a2a_kit_size_16 a2a_default_style"> \
    <a class="a2a_dd" href="https://www.addtoany.com/share"></a> \
    <a class="a2a_button_linkedin"></a> \
    <a class="a2a_button_facebook"></a> \
    <a class="a2a_button_twitter"></a> \
    <a class="a2a_button_google_gmail"></a> \
    <a class="a2a_button_google_plus"></a> \
    <a class="a2a_button_google_bookmarks"></a> \
    <a class="a2a_button_whatsapp"></a> \
    <a class="a2a_button_pinterest"></a> \
    <a class="a2a_button_email"></a> \
    <a class="a2a_button_delicious"></a> \
    <a class="a2a_button_digg"></a> \
    <a class="a2a_button_evernote"></a> \
    <a class="a2a_button_pocket"></a> \
    <a class="a2a_button_skype"></a> \
    <a class="a2a_button_line"></a> \
    <a class="a2a_button_flipboard"></a> \
    <a class="a2a_button_facebook_messenger"></a> \
    <a class="a2a_button_reddit"></a> \
    <a class="a2a_button_tumblr"></a> \
    <a class="a2a_button_baidu"></a> \
    <a class="a2a_button_wechat"></a> \
    <a class="a2a_button_telegram"></a> \
    </div> \
    </div>\
</div>');

$("#googleanalytics").html("<script> \
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){ \
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o), \
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m) \
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga'); \
 \
  ga('create', 'UA-56563407-1', 'auto'); \
  ga('send', 'pageview'); \
 \
</script>");

$("#googleheaderad").html("<p>Hello There</p>");    

$("#googlesidebarad2").html("<p>Hello There</p>");    

$("#googlefooterad").html("<p>Hello There</p>");
