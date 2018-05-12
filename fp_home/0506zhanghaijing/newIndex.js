$(function() {
    initBrowserCheck();
});
var initBrowserCheck=function(){
    var info = {};
    var str = '';
    info.shift =false;


    if($.browser.msie){

        if($.browser.version<9){
            info.str  = "IE8及其以下" ; info.version = $.browser.version;             info.shift = true ;
        }
        else{
            info.str  =  "IE9及其以上"; info.version = $.browser.version;
        }
    }
    else if($.browser.webkit){
        info.str  = "Chrome Browser";
        info.version = $.browser.version;
        if( parseInt($.browser.version) <= 43  && ( $.browser.chrome = true  )  ) {
            info.shift = true ;
        }

    }
    else if($.browser.webkit){
        info.str  = "Safari Browser";
        info.version = $.browser.version;
        if( parseInt($.browser.version) <= 5 ) {
            info.shift = true ;
        }

    }
    else if($.browser.mozilla){

        info.str = "FireFox Browser";
        info.version = $.browser.version;
        if( parseInt($.browser.version) <= 38) {
            info.shift = true ;
        }
    }
    else if($.browser.opera){
        info.str  ="Opera Browser";
        info.version = $.browser.version;
    }


    if( info.shift){
        $('.browserText').prepend( '<div class="infoBrowser"> <strong> Your browser :</strong> <span>'+ info.str+'</span> &nbsp;&nbsp;<strong>Version:</strong><span> ' + info.version +'</span> </div>');

        layer.open({
            type: 1,
            id: 'pro',
            title: 'Warnning',
            maxmin: true,
            area: ['940px', '520px'],
            content: $('#contentBrowser').html()
        });


        if( parseInt( $('#layui-layer1').css('left') ) < 0  ){
            var Layer_width = (document.documentElement.clientWidth ? document.documentElement.clientWidth : document.body.clientWidth);
            var Layer_height = (document.body.scrollHeight ? document.body.scrollHeight : document.documentElement.scrollHeight);
            var Layer_h2 = (document.body.clientHeight ? document.body.clientHeight : document.documentElement.clientHeight);
            $("#layui-layer-shade1").css({
                "width": Layer_width,
                "height": Layer_height
            });
            $('#layui-layer1').css({
                "margin-left": Layer_width / 2,
                "margin-top": Layer_h2 / 2
            });

        }

    }

}

