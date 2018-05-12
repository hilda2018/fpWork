/**
 * Created by HP on 2018/4/26.
 */
$(function() {
    // ÂÖ²¥Í¼
    picShow(picInfo);

});

var picInfo=[{
    "img":staticRoot+"home/images/bg1.jpg"+staticVersion,
    "title":"CHINA IMPORTED FRUITS MARKET PRICE<br><span>Guidance Before Quoting</span>",
    "url":rootPath+"/price/show",
    "detail":"Price trends chart for different sizes of fruits in the same country",
    "buttonInfo":"<i>View More</i>",
    "flag":1
}];

function picShow(picInfo) {
    if (!picInfo) {
        return false;
    }
    genelatePic(picInfo);
}

var $picShow = $('#picShow');
var $smallNav = $('#smallNav');
var picData = $picShow.get(0);

function genelatePic(picInfo) {
    var str1 = '', str2 = '';
    $picShow.add($smallNav).html('');
    for (var i = 0; i < picInfo.length; i++) {
        if(picInfo[i].flag == 0){

            str1 += '<li class="picLi" style="background:url('
                + picInfo[i].img
                + ') no-repeat center center;background-size:cover;"></li>';

        }else if (picInfo[i].flag == 1){
            str1 += '<li class="picLi" style="background:url('
                + picInfo[i].img
                + ') no-repeat center center;background-size:cover;"><div class="wrap"><div class="title"><h2>'
                + picInfo[i].title
                + '</h2><div class="moreDetail">';
            if(picInfo[i].url.indexOf("#") == -1){
                str1 += '<a class="smalltext" target="_blank" href='
                    + picInfo[i].url
                    + '><span class="pictext2">'
                    + picInfo[i].detail
                    + '</span><span class="picArrow">'
                    + picInfo[i].buttonInfo
                    + '<svg><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#svg_arrow"></use></svg></span></a></div></div></div></li>';
            }else{// Ê×Ò³Ãªµã
                str1 += '<a class="smalltext" href='
                    + picInfo[i].url
                    + '><span class="pictext2">'
                    + picInfo[i].detail
                    + '</span><span class="picArrow">'
                    + picInfo[i].buttonInfo
                    + '</span></a></div></div></div></li>';
            }
        }
        if (i == 0) {
            str2 += '<li class="bg"></li>';
        } else {
            str2 += '<li></li>';
        }
    }

    $picShow.html(str1);
    $smallNav.html(str2);
    picData.complete = true;
    if (!picData.complete) {
        return false;
    }

    picData.len = picInfo.length;
    picData.num = -1;

    scrollPic();

    $('#boxPrev').click(function() {
        clearInterval(picData.time);
        picData.num = $("#smallNav .bg").index();
        if (picData.num == 0) {
            picData.num = picData.len - 1;
        } else {
            picData.num = picData.num - 1;
        }
        changePic(picData.num);
    });
    $('#boxNext').click(function() {
        clearInterval(picData.time);
        picData.num = $("#smallNav .bg").index();
        picData.num = (picData.num + 1) % picData.len;
        changePic(picData.num);
    });
}

function scrollPic() {

    autoPic();

    $("#smallNav li").each(function(i, elem) {

        $('#picShow li').eq(i).attr("id", "pic" + i);
        $(elem).click(function() {
            var index = $(this).index();
            changePic(index);
        });
    });

    $("#smallNav li").hover(function() {
        clearInterval(picData.time);
    }, function() {
        autoPic();
    })
}

function autoPic() {
    picData.time = setInterval(function() {
        junmper()
    }, 6000);
}

function junmper() {
    picData.num++;
    if (picData.num > picData.len) {
        picData.num = 0;
    }

    changePic(picData.num);

}

function changePic(index) {

    var $target = $(".nav ul li").eq(index);
    var $target2 = $(".pic ul li").eq(index);
    $target.addClass("bg").siblings().removeClass("bg");

    $target2.fadeIn().siblings().hide();


}